import bpy
import sys

# Kulbako Artemy 2020 for Luxoft

def render():
    argv = sys.argv
    argv = argv[argv.index("--") + 1:]
    width = int(argv[0])
    height = int(argv[1])
    output = argv[2]
    format = argv[3]
    compress = int(argv[4])
    AAalgorithm = argv[5]
    #TODO: если материал называется также, как и текстура, то добавить
    #for material in bpy.data.materials:
     #   if material.name == "sphereMaterial":
      #      width = 10
       #     height = 10
    renderParams = bpy.context.scene.render
    bpy.context.scene.display.render_aa = AAalgorithm
    renderParams.image_settings.file_format = format
    renderParams.filepath = output
    renderParams.resolution_x = width
    renderParams.resolution_y = height
    bpy.data.scenes["Scene"].render.image_settings.compression = compress
    bpy.ops.render.render(write_still=True, use_viewport=True)


if __name__ == "__main__": render()
