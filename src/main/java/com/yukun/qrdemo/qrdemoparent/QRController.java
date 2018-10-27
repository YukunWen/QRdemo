package com.yukun.qrdemo.qrdemoparent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wyk on 2018/10/27
 */
@Controller
@RequestMapping("/qr")
@Slf4j
public class QRController {

    @GetMapping(value = "/generate")
    @ResponseBody
    public void generateQR(@RequestParam("content")String content, HttpServletResponse response) {
        BufferedImage image;
        // 禁止图像缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        image = QRCodeUtil.createImage(content);
        // 创建二进制的输出流
        try(ServletOutputStream sos = response.getOutputStream()){
            // 将图像输出到Servlet输出流中。
            ImageIO.write(image, "jpeg", sos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}