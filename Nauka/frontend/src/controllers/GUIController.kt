package controllers

import RestBridge
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sun.corba.se.spi.orbutil.threadpool.Work
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.control.cell.ComboBoxTableCell
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell
import javafx.util.Duration.minutes
import javafx.util.converter.BooleanStringConverter
import javafx.util.converter.DateStringConverter
import models.*
import requests.EmployeeDTO
import java.net.*
import java.text.ParseException
import java.util.*


class GUIController: Initializable {

    @FXML private lateinit var statusLbl: Label
    @FXML private lateinit var deptListView: ListView<Department>
    @FXML private lateinit var deptInput: TextField
    @FXML private lateinit var tabPane: TabPane
    private val parser = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
    val employeeApi = RestBridge("http://localhost:8088/employee")
    val departmentApi = RestBridge("http://localhost:8088/department/")
    private val departmentTypeToken = object: TypeToken<Set<Department?>?>(){}.type
    private val connErrMessage = "Не удалось подключиться к БД: проверьте интернет соединение или попробуйте позже"
    private val months = arrayOf("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь",
            "Октябрь", "Ноябрь", "Декабрь")
    private val manualTab = Tab("Справка")
    private lateinit var departments: Set<Department>
    private lateinit var selectedDepartment: Department
    private val roles = FXCollections.observableList(Role.values().toList())

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        val textArea = TextArea().apply {
            this.text = this@GUIController::class.java.getResource("/resources/manual.txt").readText(Charsets.UTF_8) }
        //TODO обновить справку (формат задания)
        manualTab.content = textArea
        updateDeptListView()
        fillTableByDefault()
        val tableUpdater = Timeline(KeyFrame(minutes(1.0), javafx.event.EventHandler { updateDeptListView() }))
        tableUpdater.cycleCount = Timeline.INDEFINITE
        tableUpdater.play()
    }

    private fun fillTableByDefault() {
        deptListView.items.let {
            if (it.size != 0) {
                it.first()?.let { d ->
                    selectedDepartment = d
                    fillTabs(d)
                }
            } else statusLbl.text = "Департаменты в БД отсутствуют. Добавьте хоть один, чтобы редактировать работников."
        }
    }

    private fun downloadAllDepartments() = (parser.fromJson(departmentApi.get().send(), departmentTypeToken)) as Set<Department>

    private fun removeDepartment(d: Department) = departmentApi.delete().setMessage(parser.toJson(d)).send()

    private fun addDepartment(d: Department) = departmentApi.put().setMessage(parser.toJson(d)).send()

    private fun addEmployee(req: EmployeeDTO) = employeeApi.put().setMessage(parser.toJson(req)).send()

    private fun removeEmployee(e: Employee) = employeeApi.delete().setMessage(parser.toJson(e)).send()

    private fun modifyEmployee(req: EmployeeDTO) = employeeApi.post().setMessage(parser.toJson(req)).send()

    private fun updateDeptListView() {
        try {
            deptListView.items.let {
                departments = downloadAllDepartments()
                it.clear()
                it.addAll(departments)
                statusLbl.text = "Данные успешно загружены"
            }
            deptListView.selectionModel.selectedItemProperty().addListener { _, _, newVal ->
                newVal?.let {
                    selectedDepartment = it
                    fillTabs(it)
                }
            }
        } catch (e: ConnectException) { statusLbl.text = connErrMessage }
    }

    private fun fillTabs(d: Department) {
        val tabs = months.map { Tab(it) }
        tabPane.tabs.let {
            it.clear()
            it.addAll(tabs)
            it.add(this.manualTab)
        }
        tabs.forEach {
            val employeesList = d.employees!!.toList()
            val table = TableView(FXCollections.observableList(employeesList))
            val idCol = TableColumn<Employee, Long>("табельный номер").apply {
                this.isEditable = false
                this.cellValueFactory = PropertyValueFactory("id")
            }
            val nameCol = TableColumn<Employee, String>("имя").apply {
                this.cellValueFactory = PropertyValueFactory("name")
            }
            val surnameCol = TableColumn<Employee, String>("фамилия").apply {
                this.cellValueFactory = PropertyValueFactory("surname")
            }
            val bornDateCol = TableColumn<Employee, Date>("дата рождения").apply {
                this.cellValueFactory = PropertyValueFactory("bornDate")
                this.cellFactory = TextFieldTableCell.forTableColumn(DateStringConverter())
                this.setOnEditCommit { event ->
                    event.tableView.items[event.tablePosition.row].let {
                        try {
                            it.bornDate = event.newValue
                            modifyEmployee(EmployeeDTO(selectedDepartment.id, it))
                            updateDeptListView()
                            fillTableByDefault()
                        } catch (e: ParseException) {
                            statusLbl.text = "Формат задания даты: дд.мм.гггг"
                            it.bornDate = event.oldValue
                        }
                    }
                }
            }
            val residencyCol = TableColumn<Employee, String>("адрес").apply {
                this.cellValueFactory = PropertyValueFactory("residency")
            }
            val roleCol = TableColumn<Employee, Role>("должность").apply {
                this.cellValueFactory = PropertyValueFactory("role")
                this.cellFactory = ComboBoxTableCell.forTableColumn(roles)
                this.setOnEditCommit { event ->
                    event.tableView.items[event.tablePosition.row].let {
                        it.role = event.newValue
                        modifyEmployee(EmployeeDTO(selectedDepartment.id, it))
                        updateDeptListView()
                        fillTableByDefault()
                    }
                }
            }
            val remoteStatCol = TableColumn<Employee, Boolean>("удалёнка").apply {
                this.cellValueFactory = PropertyValueFactory("remote")
                this.cellFactory = TextFieldTableCell.forTableColumn(BooleanStringConverter())
                this.setOnEditCommit { event ->
                    event.tableView.items[event.tablePosition.row].let {
                        it.remote = event.newValue
                        modifyEmployee(EmployeeDTO(selectedDepartment.id, it))
                        updateDeptListView()
                        fillTableByDefault()
                    }
                }
            }
            val daysCol = TableColumn<Employee, WorkingCalendar>("рабочие дни").apply {
                this.cellValueFactory = PropertyValueFactory("calendar")
            }
            sequenceOf(nameCol, surnameCol, residencyCol).forEach { //устанавливаем общую логику модификации для String-полей
                it.cellFactory = TextFieldTableCell.forTableColumn()
                it.setOnEditCommit { event ->
                    val modifyFieldName = (it.cellValueFactory as PropertyValueFactory).property
                    event.tableView.items[event.tablePosition.row].let { emp ->
                        emp.javaClass.getDeclaredField(modifyFieldName).let { f ->
                            f.isAccessible = true
                            f.set(emp, event.newValue)
                        }
                        modifyEmployee(EmployeeDTO(selectedDepartment.id, emp))
                        updateDeptListView()
                        fillTableByDefault()
                    }
                }
            }
            table.let { t ->
                t.isEditable = true
                t.columns.addAll(idCol, nameCol, surnameCol, bornDateCol, residencyCol, roleCol, remoteStatCol, daysCol)
                it.content = t
            }
        }
    }

    @FXML fun addDeptBtnClick() =
            deptInput.text?.let {
                if (it != "") {
                    if (!deptListView.items.map {d -> d.name }.toList().contains(it)) {
                        try {
                            addDepartment(Department(it))
                            updateDeptListView()
                            fillTableByDefault()
                        } catch (e: ConnectException) { statusLbl.text = connErrMessage }
                    } else statusLbl.text = "$it уже есть в БД"
                } else statusLbl.text = "$it - недопустимое имя"
            }

    @FXML fun remDeptBtnClick() =
            (deptListView.selectionModel.selectedItem as Department).let {
                try {
                    removeDepartment(it)
                    updateDeptListView()
                    fillTableByDefault()
                } catch (e: ConnectException) { statusLbl.text = connErrMessage }
            }

    @FXML fun addEmpBtnClick() {
        addEmployee(EmployeeDTO(selectedDepartment.id, Employee("default", "default", Date(), "default",
                Role.EMPTY, WorkingCalendar())))
        updateDeptListView()
        fillTableByDefault()
    }

    @FXML fun remEmpBtnClick() =
            ((tabPane.selectionModel.selectedItem.content as TableView<*>).selectionModel.selectedItem as Employee).let {
                try {
                    removeEmployee(it)
                    updateDeptListView()
                    fillTableByDefault()
                } catch (e: ConnectException) { statusLbl.text = connErrMessage }
            }
}