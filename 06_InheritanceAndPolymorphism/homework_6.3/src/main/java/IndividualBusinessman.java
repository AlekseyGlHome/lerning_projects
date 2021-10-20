public class IndividualBusinessman extends Client {

    @Override
    public void put(double amountToPut) {
        double commisionPersentage;
        if (amountToPut > 0) {
            if (amountToPut < 1000) {
                commisionPersentage = 1;
            } else {
                commisionPersentage = 0.5;
            }
            double percentSumma=(amountToPut*commisionPersentage)/100;
            super.put(amountToPut-percentSumma);
        }

    }

    @Override
    public void printInfo() {
        System.out.println("У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей.\n" +
                "И с комиссией 0,5%, если сумма больше либо равна 1000 рублей\n" +
                "Остаток на счете:"+getAmount());
    }
}
