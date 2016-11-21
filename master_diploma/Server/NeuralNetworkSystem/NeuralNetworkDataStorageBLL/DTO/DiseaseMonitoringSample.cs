using NeuralNetworkSystemBLL.Attributes;

namespace NeuralNetworkDataStorageBLL.DTO
{
    public class DiseaseMonitoringSample
    {
        public int SampleID { get; set; }

        [InputTrainingSample(1)]
        public double Age { get; set; }

        [InputTrainingSample(2)]
        public double Gender { get; set; }

        [InputTrainingSample(3)]
        public double Growth { get; set; }

        [InputTrainingSample(4)]
        public  double Weight { get; set; }

        [InputTrainingSample(5)]
        public double BodyMassIndex { get; set; }

        [InputTrainingSample(6)]
        public double Distance { get; set; }

        [InputTrainingSample(7)]
        public double SleepHoursCount { get; set; }

        [InputTrainingSample(8)]
        public double SleepQuality { get; set; }

        [InputTrainingSample(9)]
        public double SpentCalories { get; set; }

        [InputTrainingSample(10)]
        public double EatenCalories { get; set; }

        [InputTrainingSample(11)]
        public double FoodMultiplicity { get; set; }

        [InputTrainingSample(12)]
        public double FatAmount { get; set; }

        [InputTrainingSample(13)]
        public double CarbohydrateAmount { get; set; }

        [InputTrainingSample(14)]
        public double ProteinAmount { get; set; }

        [InputTrainingSample(15)]
        public double VitaminC { get; set; }

        [InputTrainingSample(16)]
        public double SugarLevel { get; set; }

        [InputTrainingSample(17)]
        public double StressLevel { get; set; }

        [InputTrainingSample(18)]
        public double Temperature { get; set; }

        [InputTrainingSample(19)]
        public double HightPressure { get; set; }

        [InputTrainingSample(20)]
        public double LowPressure { get; set; }

        [InputTrainingSample(21)]
        public double Pulse { get; set; }

        [OutputTrainingSample]
        public GroupRisk GroupRisk { get; set; }

        [OutputTrainingSample]
        public Disease Disease { get; set; }
    }
}
