package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/

public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        // создаём байтовый массив размером 4, т.к. у нас адрес сети должен вмещать 4 элемента каждый длинной 8 бит
        byte[] binaryNetAddress = new byte[4];
        // заполняем масив значениями, для поиска адреса сети используем логическое "И".
        for (int i = 0; i < 4; i++) {
            binaryNetAddress[i] = (byte) (ip[i] & mask[i]);
        }
        return binaryNetAddress;
    }

    public static void print(byte[] bytes) {
        //region 1-й вариант логики метода print
//        // перебираем в цикле каждый байт в массиве bytes
//        for (byte b : bytes) {
//            /*
//            сохраняем значение в строковую переменную, отформатировав вывод 8 разрядами, преобразовав
//            целочисленное значение без знака в строковое представление двоичной системы и при этом гарантируем что
//            значение будет без знака благодаря обнуление старших битов в выражении (b & 0xFF), также замещаем пробелы на 0
//             */
//            String binaryValue = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
//            // пробелы для вывода по 8 бит 4 элементов
//            System.out.print(binaryValue + " ");
//        }
//        System.out.println();
        //endregion
//region 2-й вариант логики метода print
        // перебираем в цикле каждый байт массива bytes
        for (byte b : bytes) {
            // во вложенном в него массиве, перебираем каждый бит массива bytes начиная со старшего бита (индекс 7) до младшего бита (индекс 0)
            for (int i = 7; i >= 0; i--) {
                // сдвигаем байт вправо на i позиций и логическим (И) & 1 получаем последний бит в сдвинутом байте
                int bit = (b >> i) & 1;
                System.out.print(bit);
            }
            System.out.print(" ");
        }
        System.out.println();
//endregion
    }
}

//Задача (Hard)
//        Java Multithreading, 1 уровень, 2 лекция
//
//    Определяем адрес сети
//
//        1. Даны IP-адрес и маска подсети, необходимо вычислить адрес сети - реализуй метод getNetAddress.
//        Используй операцию поразрядной конъюнкции (логическое И).
//
//        Пример:
//        IP-адрес: 11000000 10101000 00000001 00000010 (192.168.1.2)
//        Маска подсети: 11111111 11111111 11111110 00000000 (255.255.254.0)
//        Адрес сети: 11000000 10101000 00000000 00000000 (192.168.0.0)
//
//        2. Реализовать метод print, который выведет в консоль данные в двоичном коде. Для IP-адреса(192.168.1.2)
//        должна быть выведена строка "11000000 10101000 00000001 00000010"
//        3. Метод main не участвует в тестировании
//
//        Requirements:
//        1. Метод getNetAddress должен вычислять и возвращать адрес сети согласно переданным
//        параметрам(IP-адрес и маска подсети).
//        2. Метод getNetAddress должен быть статическим и публичным.
//        3. Метод print должен быть статическим и публичным.
//        4. Метод print должен преобразовывать переданный ему IP адрес в двоичный код и выводить
//        на экран(как в условии).
//region РЕШЕНИЕ JAVARUSH
//package com.javarush.task.task21.task2101;
//
///*
//Определяем адрес сети
//*/
//
//public class Solution {
//    public static void main(String[] args) {
//        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
//        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
//        byte[] netAddress = getNetAddress(ip, mask);
//        print(ip);          //11000000 10101000 00000001 00000010
//        print(mask);        //11111111 11111111 11111110 00000000
//        print(netAddress);  //11000000 10101000 00000000 00000000
//    }
//
//    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
//        byte[] netAddress = new byte[4];
//        for (int i = 0; i < netAddress.length; i++)
//            netAddress[i] = (byte) (ip[i] & mask[i]);
//        return netAddress;
//    }
//
//    public static void print(byte[] bytes) {
//        String currentBinary;
//        for (byte aByte : bytes) {
//            currentBinary = Integer.toBinaryString(256 + (int) aByte);
//            System.out.print(currentBinary.substring(currentBinary.length() - 8) + " ");
//        }
//        System.out.println();
//    }
//}
//endregion