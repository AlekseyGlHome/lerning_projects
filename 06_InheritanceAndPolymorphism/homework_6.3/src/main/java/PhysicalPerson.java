public class PhysicalPerson extends Client {
    @Override
    public void printInfo() {
        System.out.println("У физических лиц пополнение и снятие происходит без комиссии\n" +
                "Остаток на счете:"+getAmount());
    }
}
