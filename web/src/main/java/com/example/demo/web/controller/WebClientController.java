/******************************************************************************
 * Copyright (C) 2013 - 2019 ShenZhen OnePlus Technology Co.,Ltd All Rights Reserved.
 * 本软件为深圳万普拉斯科技有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.example.demo.web.controller;


//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author liujh
 * @version V1.0
 * @Title: HelloController.java
 * @Package com.example.demo.controller
 * @Description
 * @date 2019 04-27 23:48.
 */
//@RestController
@RequestMapping("/webclient")
public class WebClientController {

    WebClient webClient = WebClient.create();;

    @RequestMapping("/get")
    public String get() {
        Mono<String> resp = WebClient.create()
                .method(HttpMethod.GET)
                .uri("http://baidu.com")
                .cookie("token","xxxx")
                .cookie("JSESSIONID","XXXX")
                .retrieve()
                .bodyToMono(String.class);

        String res = resp.block();
        return res;
    }


    @RequestMapping("/header")
    public String testWithHeaderFilter(){
        WebClient webClient = WebClient.builder()
                .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .filter((clientRequest, next) -> {
                    //LOGGER.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                    clientRequest.headers()
                            .forEach((name, values) -> values.forEach(value -> System.out.println(name  + "=" +  value)));
                    return next.exchange(clientRequest);
                })
                .build();

        Mono<String> resp = webClient.get()
                .uri("https://baidu.com")
                .retrieve()
                .bodyToMono(String.class);
        //LOGGER.info("result:{}",resp.block());

        return resp.block();
    }

    @RequestMapping("/post")
    public String post(){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("name1","value1");
        formData.add("name2","value2");
        Mono<String> resp = WebClient.create().post()
                .uri("http://www.w3school.com.cn/test/demo_form.asp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve().bodyToMono(String.class);

        return resp.block();
    }

    @RequestMapping("upload")
    public String testUploadFile(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        HttpEntity<ClassPathResource> entity = new HttpEntity<>(new ClassPathResource("parallel.png"), headers);
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", entity);
        Mono<String> resp = WebClient.create().post()
                .uri("http://localhost:8080/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(parts))
                .retrieve().bodyToMono(String.class);
       // LOGGER.info("result:{}",resp.block());

        return resp.block();
    }

    @RequestMapping("/downloadFile")
    public void testDownloadFile() throws IOException {
        Mono<ClientResponse> resp = WebClient.create().get()
                .uri("http://localhost:8080/file/download")
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .exchange();
        ClientResponse response = resp.block();

        String disposition = response.headers().asHttpHeaders().getFirst("Content-Disposition");
        String fileName = disposition.substring(disposition.indexOf("=")+1);
        Resource resource = response.bodyToMono(Resource.class).block();
        File out = new File(fileName);
        FileUtils.copyInputStreamToFile(resource.getInputStream(), out);
        //LOGGER.info(out.getAbsolutePath());
    }

    @RequestMapping("/downloadImage")
    public void testDownloadImage() throws IOException {
        Mono<Resource> resp = WebClient.create().get()
                .uri("http://static.oneplus.cn/data/attachment/portal/201908/19/183034toj7otksp87ykyyz.jpg")
                .accept(MediaType.IMAGE_JPEG)
                .retrieve().bodyToMono(Resource.class);
        Resource resource = resp.block();
        BufferedImage bufferedImage = ImageIO.read(resource.getInputStream());
        ImageIO.write(bufferedImage, "png", new File("captcha.png"));
    }
}
