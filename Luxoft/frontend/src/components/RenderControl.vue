<template>
  <div id="content">
    <Notification message="Отправить архив для рендеринга" :is-error="false" :is-visible="true"/>
    <hr/>
    <div id="wrapper">
      <div id="controlContainer">
        <table id="controlTable">
          <tr>
            <td colspan="2">
              <label> Архив в сценой и/или текстурами:
                <input name="INPUT_FILE" type="file">
              </label>
            </td>
          </tr>
          <tr>
            <td>
              <label> Ширина:
                <input name="WIDTH" type="number" placeholder="[2 ; 15360]">
              </label>
            </td>
            <td>
              <label> Высота:
                <input name="HEIGHT" type="number" placeholder="[2 ; 15360]">
              </label>
            </td>
          </tr>
          <tr>
            <td>
              <label> Формат:
                <select name="FORMAT">
                  <option value="JPEG" selected="true">JPEG</option>
                  <option value="PNG">PNG</option>
                  <option value="BMP">BMP</option>
                </select>
              </label>
            </td>
            <td>
              <label> Сжатие:
                <input name="COMPRESSION" type="number" placeholder=" min [0 ; 100] max">
              </label>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <label> Сглаживание:
                <select name="ANTIALIASING_ALGORITHM">
                  <option value="OFF" selected="true">OFF</option>
                  <option value="FXAA">FXAA</option>
                  <option value="SSAA 5x">SSAA 5x</option>
                  <option value="SSAA 8x">SSAA 8x</option>
                  <option value="SSAA 11x">SSAA 11x</option>
                  <option value="SSAA 16x">SSAA 16x</option>
                  <option value="SSAA 32x">SSAA 32x</option>
                </select>
              </label>
            </td>
          </tr>
        </table>
        <CheckButton color="red" label="Отправить" @click.native="validateForm"/>
      </div>
      <div id="svgContainer">
        <svg id="graph" xmlns="http://www.w3.org/2000/svg" class="bordered rounded">
          <image xlink:href="/assets/img/blender_community_badge.png" width="500px" height="281px"/>
        </svg>
      </div>
    </div>
    <hr/>
    <div id="outputContainer">
      <Notification message="Точки отсутствуют" :is-error="false" :is-visible="points.length === 0"/>
      <Notification v-bind="errorTableNotification"/>
      <table v-if="points.length !== 0" id="outputTable">
        <thead>
        <tr>
          <th>x</th>
          <th>y</th>
          <th>r</th>
          <th>Точка входит в ОДЗ</th>
          <th>Дата рождения</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="point in points" :key="point">
          <td>{{ point.x }}</td>
          <td>{{ point.y }}</td>
          <td>{{ point.r }}</td>
          <td>{{ (point.status) ? "\u2611" : "\u2610" }}</td>
          <td>{{ new Date(Date.parse(point.bornDate)).toLocaleString() }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import CheckButton from "@/components/CheckButton";
import Notification from "@/components/Notification";

export default {
  name: "RenderControl",
  components: {Notification, CheckButton},
  data: function () {
    return {
      errorTableNotification: {
        message: undefined,
        isError: true,
        isVisible: false,
      },
      formData: new FormData()
    }
  },
  methods: {
    validateForm: function () {
      /*if ((this.point.x >= -4 && this.point.x <= 4) && this.point.y > -5 && this.point.y < 3) this.sendForm();
      else {
        this.errorTableNotification.message = "Данные введены некорректно";
        this.errorTableNotification.isVisible = true;
      }*/
    },
    sendForm: function () {
      /*this.$axios.put("point", {
        x: this.point.x,
        y: this.point.y,
        r: this.point.r
      }, {
        headers: {Authorization: "Bearer " + localStorage.getItem("jwt")}
      }).then(() => {
        this.loadPoints();
      }).catch(error => {
        let answer = error.response.data.errors[0];
        this.errorTableNotification.message = `${answer.field} ${answer.defaultMessage}`;
        this.errorTableNotification.isVisible = true;
      });*/
    }
  }
}
</script>

<style scoped>
#content {
  margin-left: 5%;
  margin-right: 5%;
}

#wrapper {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

#wrapper * {
  min-width: 50%;
}

#outputContainer {
  margin-bottom: 130px;
}

input, svg {
  background-color: white;
}

svg {
  width: 500px;
  height: 281px;
  box-shadow: inset 0 0 7px 1px gray;
}

#svgContainer * {
  margin: 2px
}

#controlTable * {
  margin: 4%
}

#outputTable {
  border: 1px solid #000720;
  border-collapse: collapse;
  margin: auto;
  width: 90%;
}

#outputTable th {
  background-color: #000720;
  color: white;
}

#outputTable * {
  padding: 25px;
}
</style>