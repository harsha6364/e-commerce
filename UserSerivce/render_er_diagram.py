from PIL import Image, ImageDraw, ImageFont

W, H = 1600, 1000
img = Image.new("RGB", (W, H), "#fafbfe")
d = ImageDraw.Draw(img)

def font(size, bold=False):
    name = "arialbd.ttf" if bold else "arial.ttf"
    return ImageFont.truetype(f"C:/Windows/Fonts/{name}", size)

TITLE = font(31, True); SUB = font(15); HEAD = font(20, True)
FIELD = font(16, True); TYPE = font(15); SMALL = font(14); TAG = font(14, True); LABEL = font(15, True)
NAVY, EDGE, RULE = "#102e68", "#26364d", "#aeb8c8"

def text(x, y, value, f=FIELD, fill="#172337", anchor=None):
    d.text((x, y), value, font=f, fill=fill, anchor=anchor)

def entity(x, y, w, h, title, color, rows, footer=None):
    d.rounded_rectangle((x, y, x+w, y+h), 7, fill="white", outline=EDGE, width=2)
    d.rounded_rectangle((x, y, x+w, y+55), 7, fill=color)
    d.rectangle((x, y+48, x+w, y+55), fill=color)
    text(x+w//2, y+28, title, HEAD, "white", "ma")
    split = x + int(w*.63)
    d.line((split, y+55, split, y+h), fill=RULE, width=1)
    row_y = y+78
    for name, key, datatype in rows:
        fill = "#0765c1" if key == "FK" else "#172337"
        text(x+22, row_y, name, FIELD, fill)
        if key == "PK":
            text(split-84, row_y, "⚿", TAG, "#c88400")
            text(split-50, row_y, "PK", TAG, "#172337")
        elif key:
            text(split-50, row_y, key, TAG, "#0765c1")
        text(split+20, row_y, datatype, TYPE, "#344158")
        row_y += 42
    if footer:
        d.line((x+20, y+h-55, x+w-20, y+h-55), fill=RULE, width=1)
        text(x+22, y+h-38, footer, SMALL, "#3f506a")

text(W//2, 40, "USER SERVICE DATABASE SCHEMA", TITLE, NAVY, "ma")
text(W//2, 76, "Generated from the current JPA entity mappings", SUB, "#53627a", "ma")

entity(110, 190, 415, 235, "ROLE", "#7d3fb2", [
    ("role_id", "PK", "INT"), ("role_name", "", "ENUM")],
    "Values: CUSTOMER, ADMIN, SALLER")
entity(735, 135, 475, 390, "USER", "#2878c9", [
    ("user_id", "PK", "INT"), ("email", "UNIQUE", "VARCHAR"), ("password", "", "VARCHAR"),
    ("created_at", "", "DATETIME"), ("modified_at", "", "DATETIME"), ("role_id", "FK", "INT")],
    "role_id → ROLE.role_id")
entity(565, 620, 505, 325, "PROFILE", "#08959d", [
    ("profile_id", "PK", "INT"), ("first_name", "", "VARCHAR"), ("last_name", "", "VARCHAR"),
    ("phone", "", "VARCHAR"), ("dob", "", "DATE")],
    "image_url, image_public_id, user_id (FK + UNIQUE)")

# ROLE (1) → USER (many)
d.line((525, 307, 650, 307, 650, 330, 735, 330), fill=EDGE, width=3)
d.line((720, 320, 735, 330, 720, 340), fill=EDGE, width=3)
d.line((720, 340, 735, 330), fill=EDGE, width=3)
text(548, 283, "1", LABEL); text(688, 304, "M", LABEL); text(560, 347, "assigned to", SMALL)

# USER (1) → PROFILE (1)
d.line((972, 525, 972, 570, 817, 570, 817, 620), fill=EDGE, width=3)
d.line((960, 542, 984, 542), fill=EDGE, width=3); d.line((805, 662, 829, 662), fill=EDGE, width=3)
text(990, 560, "1", LABEL); text(837, 595, "1", LABEL); text(840, 550, "has", SMALL)

# Legend
d.rounded_rectangle((1120, 695, 1475, 905), 8, fill="white", outline="#9aa8ba", width=2)
text(1150, 724, "LEGEND", font(18, True), NAVY)
text(1150, 767, "⚿", font(17, True), "#c88400"); text(1182, 767, "PK", FIELD); text(1265, 767, "Primary key", SMALL)
text(1150, 809, "FK", TAG, "#0765c1"); text(1182, 809, "FK", FIELD); text(1265, 809, "Foreign key", SMALL)
text(1150, 851, "1", LABEL); text(1182, 851, "One", SMALL); text(1295, 851, "M", LABEL); text(1323, 851, "Many", SMALL)
text(1150, 881, "USER ↔ PROFILE is one-to-one", SMALL)

img.save("user-service-er-diagram.png", "PNG")
