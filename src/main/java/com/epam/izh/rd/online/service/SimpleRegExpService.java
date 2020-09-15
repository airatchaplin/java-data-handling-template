package com.epam.izh.rd.online.service;

import java.io.*;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String reg1 = "0234 2145";
        String reg2 = "2012 0532";

        String text = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/sensitive_data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            text = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        text = text.replace(reg1, "**** ****");
        text = text.replace(reg2, "**** ****");

        return text;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String text = null;
        String reg1 = "\\$\\{payment_amount\\}";
        String reg2 = "\\$\\{balance\\}";

        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/sensitive_data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            text = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        text = text.replaceAll(reg1, String.valueOf((int) paymentAmount));
        text = text.replaceAll(reg2, String.valueOf((int) balance));

        return text;
    }
}
