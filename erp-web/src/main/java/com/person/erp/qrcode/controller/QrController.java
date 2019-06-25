package com.person.erp.qrcode.controller;

import ch.qos.logback.core.util.FileUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itexplore.core.api.utils.ResultUtils;
import com.itexplore.core.common.utils.io.FileUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.qrcode.constants.QrConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

/**
 * 生成二维码
 */
@RequestMapping("/api/qrcode")
@RestController
public class QrController {
    @GetMapping
    private void getQrCode(@RequestParam(value = "url", required = true) String url,
                           @RequestParam(value = "width", defaultValue = "80", required = false) Integer width,
                           @RequestParam(value = "height", defaultValue = "80", required = false) Integer height,
                           HttpServletResponse response) {
        String format = "jpg";
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        ServletOutputStream outputStream = null;
        try {
            BitMatrix bm = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = toImage(bm);
            outputStream = response.getOutputStream();
            ImageIO.write(image, format, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("＝＝＝＝＝＝＝＝＝生成二维码失败！＝＝＝＝＝＝＝＝");
        } finally {
            FileUtils.close(outputStream);
        }
    }

    private BufferedImage toImage(BitMatrix bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j,  bm.get(i, j) ? QrConstants.BLACK.getType() : QrConstants.WHITE.getType());
            }
        }
        return image;
    }
}
