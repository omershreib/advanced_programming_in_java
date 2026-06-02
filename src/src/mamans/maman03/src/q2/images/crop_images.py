from PIL import Image

img = Image.open("products.png")

icons = {
#    "bread":    (20, 100, 240, 300),
#      "bread":    (20, 100, 300, 400),
#       "bread":    (50, 150, 300, 400),
#      "eggs":     (300, 150, 500, 400),
#      "milk":     (520, 150, 760, 400),
#      "cheese":   (760, 150, 950, 400),
#
#      "cereal":   (50, 500, 300, 680),
#     "wine":     (300, 450, 500, 680),
#     "carrots":  (500, 450, 760, 680),
#     "cucumber": (760, 500, 1000, 680),
#
#     "onion":    (50, 750, 300, 960),
#     "tomatoes": (300, 750, 500, 960),
#     "sausage":  (520, 750, 760, 960),
#     "bamba":    (760, 750, 1000, 960),
#
     "cart":     (350, 1100, 650, 1300)
}

for name, box in icons.items():
    img.crop(box).save(name + ".png")