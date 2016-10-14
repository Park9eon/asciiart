import java.awt.Color
import java.awt.image.BufferedImage
import java.io.BufferedWriter
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import javax.imageio.ImageIO

/**
 * Created by park9eon on 14/10/2016.
 */
fun main(vararg args: String) {
    try {
        val out = BufferedWriter(FileWriter("profile.html"))
        val img = ImageIO.read(File("image.jpg"))// args[1]))
        out.write("<table style='width:100%;height:100%'>")
        for (y in 0..img.height - 1) {
            out.write("<tr>")
            for (x in 0..img.width - 1) {
                val rgb = img.getRGB(x, y)
                // img.setRGB(x, y, gray)
                out.write(colorToHtml(rgb))
            }
            out.write("</tr>")
        }
        out.write("</table>")
        out.close()
        // saveImage(img)
    } catch (e: FileNotFoundException) {
        println("이미지경로가 잘못되었습니다. : ${e.message}")
    } catch (e: IndexOutOfBoundsException) {
        println("파일을 선택해주세요. : ${e.message}")
    } catch (e: Exception) {
        println("에러발생 삐용!삐용! : ${e.message}")
    }
}

fun colorToGray(rgb: Int): Int {
    val color = Color(rgb)
    val red = color.red
    val green = color.green
    val blue = color.blue
    val gray = (red + green + blue) / 3
    return Color(gray, gray, gray).rgb
}

fun colorToChar(rgb: Int): Char {
    val color = Color(rgb)
    val red = color.red
    val green = color.green
    val blue = color.blue
    val g = (red + green + blue) / 3
    return if (g >= 230.0) { ' '
    } else if (g >= 200.0) { '.'
    } else if (g >= 180.0) { '*'
    } else if (g >= 160.0) { ':'
    } else if (g >= 130.0) { 'o'
    } else if (g >= 100.0) { '&'
    } else if (g >= 70.0) { '8'
    } else if (g >= 50.0) { '#'
    } else { '@' }
}

fun colorToHtml(rgb: Int): String {
    val color = Color(rgb)
    val red = color.red
    val green = color.green
    val blue = color.blue
    val g = (red + green + blue) / 3
    val char = if (g >= 230.0) { "&nbsp;"
    } else if (g >= 200.0) { "."
    } else if (g >= 180.0) { "*"
    } else if (g >= 160.0) { ":"
    } else if (g >= 130.0) { "o"
    } else if (g >= 100.0) { "&"
    } else if (g >= 70.0) { "8"
    } else if (g >= 50.0) { "#"
    } else { "@" }
    return "<th style='color:rgb($red,$green,$blue);'>$char</th>".format(color)
}


fun saveImage(bufferedImage: BufferedImage) {
    val outputFile = File("newImage${"%.2f".format(Math.random())}.png")
    ImageIO.write(bufferedImage, "png", outputFile)
}