import java.util.Random;

public class Hospital {

    private static final float MIN_TEMPERATURE = 32f;
    private static final float MAX_TEMPERATURE = 40f;
    private static final float NORMAL_TEMP_MIN = 36.2f;
    private static final float NORMAL_TEMP_MAX = 36.9f;

    public static float[] generatePatientsTemperatures(int patientsCount) {

        Random random = new Random();
        float[] patientTemperature = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            float temperature =
                    random.nextFloat() * (MAX_TEMPERATURE - MIN_TEMPERATURE) + MIN_TEMPERATURE;
            patientTemperature[i] = temperature;
        }

        return patientTemperature;
    }

    public static String getReport(float[] temperatureData) {
        float summaAvgTemperature = 0;
        int countNormPatient = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (float patient : temperatureData) {
            summaAvgTemperature += patient;
            if (patient > (NORMAL_TEMP_MIN - 0.1) && patient < (NORMAL_TEMP_MAX + 0.1)) {
                countNormPatient++;
            }

            double resultTemperatura = roundTemperature(patient, 1);
            stringBuilder.append(resultTemperatura).append(" ");
        }
        float avgTemperature = summaAvgTemperature / temperatureData.length;
        double resultAvgTemperatura = roundTemperature(avgTemperature, 2);
        String report =
                "Температуры пациентов: " + stringBuilder.toString().trim() +
                        "\nСредняя температура: " + resultAvgTemperatura +
                        "\nКоличество здоровых: " + countNormPatient;

        return report;
    }

    private static double roundTemperature(float temoerature, int count) {
        double scale = Math.pow(10, count);
        return Math.round(temoerature * scale) / scale;

    }
}
