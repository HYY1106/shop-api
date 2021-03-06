package com.fh.shop.api.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

public class RSAUtil {

    //解密
    public static String decrypt(String data){
        //加密  公钥
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFSYQU/jqaqqdnlmCTk7W1MEkwhdxlzpaQHmjTdi9xoghw4AbnPdaOaRNhUkCvmS4fS3+tbP+eELhodn2njfYjmHB9S3sB+tIjcqoLoXuwugHK02argIM67GzWE1seYQGj6PPMViQOH2qyICQjInSxob0ztIYZE69ZbtLb1LH3oQIDAQAB";
        //解密   私钥
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIVJhBT+Opqqp2eWYJOTtbUwSTCF3GXOlpAeaNN2L3GiCHDgBuc91o5pE2FSQK+ZLh9Lf61s/54QuGh2faeN9iOYcH1LewH60iNyqguhe7C6AcrTZquAgzrsbNYTWx5hAaPo88xWJA4farIgJCMidLGhvTO0hhkTr1lu0tvUsfehAgMBAAECgYBLvd0a+OVgov2CdgfnlRa7IfdVZZw7FkylFULId+66CjP1wLjri05zmu5ybYVoxAlbq1GgwcH4ltPUORFqt9dYwsqA5GanmyWXIc+FyGuvO6M27915QF0et6hnFH3SqXV5WSTe0Gpj91lmH1XQZhr+Hl9P5OcTbvRYio/RnM1l4QJBAPwTQHIx8hmungBi0n+MgC2FPj58MswMzqbiF7c96qMRDp1k6s/eNIh1ktk0P6u4uq9q5PnI72ABGFQzHVzyjMUCQQCHXMlDiE1qn5uqypnghoBqQ866sT63osQrpZ2esGSl8t+gOuUsMC48xoi7eoY9Xkxr945B0b3AxSzxfN1Fx+UtAkAoRFcGJu5apec9fovLrkEHhIJl7ucaa21TgUhykCR8OgxQnj9YR1Gy/r0fj1Ygc1j3DqdXM37xohsl0Ch/oYtVAkBgtTO9odXdhWaL4O+K780zHnrF+vfZ7tnEbTBtPYzK2vhV23370fhWx2jMuL7LfWGQCW+DKrwsyzJO3Wyg/C2JAkEAjL61Kl69UmMu/62EbtifgYh2O9M0cudNuz3Ohgb5mX69MGhn0FRGRljvl0m7rVGUEq5vhJbeO0FGAtvsb53Yew==";
        RSA rsa = new RSA(privateKey,publicKey);
        byte[] bytes = rsa.decryptFromBase64(data, KeyType.PrivateKey);
        return new String(bytes);
    }
}
