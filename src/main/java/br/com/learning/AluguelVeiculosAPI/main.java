package br.com.learning.AluguelVeiculosAPI;

import br.com.learning.AluguelVeiculosAPI.model.Motorcycle;

import java.math.BigDecimal;

public class main {
    public static void main(String[] args) {
        Motorcycle moto = new Motorcycle(1, "adg-1231", "YAMAHA", (short) 2000, new BigDecimal(100000), true, null);
        System.out.println(moto.calculateRentValue((short) 4));
    }
}
