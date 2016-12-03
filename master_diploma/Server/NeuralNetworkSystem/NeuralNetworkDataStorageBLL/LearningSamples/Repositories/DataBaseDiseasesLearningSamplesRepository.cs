using System;
using System.Collections.Generic;
using NeuralNetworkSystemBLL.Interfaces.Learning;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using NeuralNetworkDataStorageBLL.DTO;
using NeuralNetworkDataStorageBLL.Enums;
using NeuralNetworkDataStorageBLL.LearningSamples.Mappers;
using NeuralNetworkSystemBLL.NeuralNetworkComponents;
using NeuralNetworkSystemBLL.Attributes;

namespace NeuralNetworkDataStorageBLL.LearningSamples.Repositories
{
    public class DataBaseDiseasesLearningSamplesRepository : ILearningSamplesRepository
    {
        public List<ILearningSample> LearningSamples { get; set; }
        public List<DiseaseMonitoringSample> SamplesList { get; set; }
        private readonly string _connectionString;
        public  DiseasesTypeMapper Mapper;
        public NetworkTypeEnum NetworkType;
        private readonly Random _random;


        public DataBaseDiseasesLearningSamplesRepository()
        {
           // Mapper = new DiseasesTypeMapper(new DiseasesOutputMapper());
            _random = new Random();
            _connectionString = ConfigurationManager.ConnectionStrings["NeuralNetworkDB"].ConnectionString;
    
            LearningSamples = new List<ILearningSample>();
        }

        public List<DiseaseMonitoringSample> GetAll()
        {
            var resultList = new List<DiseaseMonitoringSample>();
            var queryString = "SELECT LearningSamples.*, DiseaseStatus.*,GroupRisk.*  " +
                              " FROM LearningSamples LEFT JOIN DiseaseStatus ON LearningSamples.DiseaseStatusID = DiseaseStatus.DiseaseStatusID LEFT JOIN GroupRisk ON LearningSamples.GroupRiskID = GroupRisk.GroupRiskID ";

            if (NetworkType != NetworkTypeEnum.DiseaseNeuralNetwork)
            {
                queryString += "WHERE LearningSamples.DiseaseStatusID = " + (int) NetworkType;
            }

            using (var connection = new SqlConnection(_connectionString))
            {
                var command = new SqlCommand(queryString, connection);
                connection.Open();
                var dataReader = command.ExecuteReader();
                while (dataReader.Read())
                {
                    resultList.Add(GetSampleFromReader(dataReader));
                }
            }

            SamplesList = resultList.ToList();
            return resultList;
        }

        public void Generate()
        {
            var list = new List<DiseaseMonitoringSample>();

            for (var i = 0; i < 50; i++)
            {

                var newSample = new DiseaseMonitoringSample();
                newSample.Age = _random.Next(60, 90);
                newSample.Gender = 1;
                newSample.Growth = Convert.ToDouble(_random.Next(150, 160)) / 100;
                newSample.Weight = _random.Next(80, 90);
                newSample.BodyMassIndex = newSample.Weight / (newSample.Growth * newSample.Growth);
                newSample.Distance = _random.Next(1, 1);
                newSample.SleepHoursCount = _random.Next(9, 11);
                newSample.SleepQuality = _random.Next(6, 7);
                newSample.SpentCalories = _random.Next(1500, 1600);
                newSample.EatenCalories = _random.Next(3000, 3500);
                newSample.FoodMultiplicity = _random.Next(4, 6);
                newSample.FatAmount = newSample.Weight + _random.Next(30, 50);
                newSample.CarbohydrateAmount = newSample.Weight * 4 + _random.Next(-15, 15);
                newSample.ProteinAmount = newSample.Weight + _random.Next(-10, 10);
                newSample.VitaminC = _random.Next(55, 65);
                newSample.SugarLevel = _random.Next(4, 5);
                newSample.StressLevel = _random.Next(6, 10);
                newSample.Temperature = 36.6;
                newSample.HightPressure = _random.Next(130, 135);
                newSample.LowPressure = _random.Next(85, 100);
                newSample.Pulse = _random.Next(70, 90);
                newSample.Disease = new Disease
                {
                    Status = DiseasesStatusEnum.Infarct
                };
                newSample.GroupRisk = new GroupRisk
                {
                    GroupRiskType = RiskStatusEnum.Hight
                };


                list.Add(newSample);
            }

            foreach (var sample in list)
            {
                AddSample(sample);
            }
        }

        public ILearningSamplesRepository PopulateSamples()
        {
            var samplesList = GetAll();

            foreach (var sample in samplesList)
            {
                LearningSamples.Add(Mapper.MapToLearningSample<NeuralLayer, Neuron>(sample, new LearningSample()));
            }

            return this;
        }

        public static DiseaseMonitoringSample GetSampleFromReader(IDataRecord reader)
        {
            var newSample = new DiseaseMonitoringSample
            {
                SampleID = reader["SampleID"] != null ? Convert.ToInt32(reader["SampleID"]) : 0,
                Age = reader["Age"] != null ? Convert.ToDouble(reader["Age"]) : 0,
                Gender = reader["Gender"] != null ? Convert.ToDouble(reader["Gender"]) : 0,
                Growth = reader["Growth"] != null ? Convert.ToDouble(reader["Growth"]) : 0,
                Weight = reader["Weight"] != null ? Convert.ToDouble(reader["Weight"]) : 0,
                BodyMassIndex = reader["BodyMassIndex"] != null ? Convert.ToDouble(reader["BodyMassIndex"]) : 0,
                Distance = reader["Distance"] != null ? Convert.ToDouble(reader["Distance"]) : 0,
                SleepHoursCount = reader["SleepHoursCount"] != null ? Convert.ToDouble(reader["SleepHoursCount"]) : 0,
                SleepQuality = reader["SleepQuality"] != null ? Convert.ToDouble(reader["SleepQuality"]) : 0,
                SpentCalories = reader["SpentCalories"] != null ? Convert.ToDouble(reader["SpentCalories"]) : 0,
                EatenCalories = reader["EatenCalories"] != null ? Convert.ToDouble(reader["EatenCalories"]) : 0,
                FoodMultiplicity = reader["FoodMultiplicity"] != null ? Convert.ToDouble(reader["FoodMultiplicity"]) : 0,
                FatAmount = reader["FatAmount"] != null ? Convert.ToDouble(reader["FatAmount"]) : 0,
                CarbohydrateAmount = reader["CarbohydrateAmount"] != null ? Convert.ToDouble(reader["CarbohydrateAmount"]) : 0,
                ProteinAmount = reader["ProteinAmount"] != null ? Convert.ToDouble(reader["ProteinAmount"]) : 0,
                VitaminC = reader["VitaminC"] != null ? Convert.ToDouble(reader["VitaminC"]) : 0,
                SugarLevel = reader["SugarLevel"] != null ? Convert.ToDouble(reader["SugarLevel"]) : 0,
                StressLevel = reader["StressLevel"] != null ? Convert.ToDouble(reader["StressLevel"]) : 0,
                Temperature = reader["Temperature"] != null ? Convert.ToDouble(reader["Temperature"]) : 0,
                HightPressure = reader["HightPressure"] != null ? Convert.ToDouble(reader["HightPressure"]) : 0,
                LowPressure = reader["LowPressure"] != null ? Convert.ToDouble(reader["LowPressure"]) : 0,
                Pulse = reader["Pulse"] != null ? Convert.ToDouble(reader["Pulse"]) : 0,

                GroupRisk = new GroupRisk
                {
                   GroupRiskType = reader["GroupRiskID"] != null ? (RiskStatusEnum)Enum.ToObject(typeof(RiskStatusEnum), Convert.ToInt32(reader["GroupRiskID"])) : 0,
                   Name = reader["RiskName"]?.ToString() ?? string.Empty
                },   
                          
                Disease = new Disease
                {
                    Status = reader["DiseaseStatusID"] != null ? 
                    (DiseasesStatusEnum)Enum.ToObject(typeof(DiseasesStatusEnum), Convert.ToInt32(reader["DiseaseStatusID"])) : 0,
                    Name = reader["DiseaseName"]?.ToString() ?? string.Empty
                }
            };

            return newSample;
        }

        public void SaveNewSample(DiseaseMonitoringSample newSample)
        {
            var fromDbList = GetAll();
            var allSamples = ToDistanceObject(fromDbList).ToList();

            foreach (var sample in allSamples)
            {
                sample.Distance = CalculateDistance(newSample, sample.LearningSample);
            }

            var toDelete = allSamples.OrderBy(s => s.Distance).Take(GetFivePercentCount(allSamples)).ToList().Last();

            DeleteSample(toDelete.LearningSample);
            AddSample(newSample);
        }

        private int GetFivePercentCount(IEnumerable<LearningSampleSpaceDistance> samples)
        {
            var count = samples.Count();
            var fivePercentCount = count * 5/100;
            return fivePercentCount;
        }

        private double CalculateDistance(DiseaseMonitoringSample from, DiseaseMonitoringSample to)
        {
            double sum = 0;
            var typeProperties = to.GetType().GetProperties();

            foreach (var prop in typeProperties)
            {
                var attributes = prop.GetCustomAttributes(true);
                foreach (var attribute in attributes)
                {
                    var inputAttibute = attribute as InputTrainingSampleAttribute;

                    if (inputAttibute != null)
                    {
                        var toValue = Convert.ToDouble(prop.GetValue(to, null));
                        var fromValue = Convert.ToDouble(from.GetType().GetProperty(prop.Name).GetValue(from));

                        sum += Math.Pow(toValue - fromValue, 2);
                    }
                }
            }

            var sqrt = Math.Sqrt(sum);

            return sqrt;
        }

        private IEnumerable<LearningSampleSpaceDistance> ToDistanceObject(IEnumerable<DiseaseMonitoringSample> samples)
        {
            return samples.Select(sample => new LearningSampleSpaceDistance() {LearningSample = sample});           
        }

        private void AddSample(DiseaseMonitoringSample sample)
        {
            var queryString =
                "INSERT INTO LearningSamples(Age, Gender, Growth, Weight, BodyMassIndex, Distance, SleepHoursCount, SleepQuality, SpentCalories," +
                " EatenCalories, FoodMultiplicity, FatAmount, CarbohydrateAmount, ProteinAmount, VitaminC, SugarLevel, StressLevel, Temperature, HightPressure, LowPressure, Pulse, DiseaseStatusID, GroupRiskID )" +
                " VALUES (@Age, @Gender, @Growth, @Weight, @BodyMassIndex, @Distance, @SleepHoursCount, @SleepQuality, @SpentCalories, @EatenCalories, @FoodMultiplicity," +
                " @FatAmount, @CarbohydrateAmount, @ProteinAmount, @VitaminC, @SugarLevel, @StressLevel, @Temperature, @HightPressure, @LowPressure, @Pulse, @DiseaseStatusID, @GroupRiskID )";

            using (var connection = new SqlConnection(_connectionString))
            {
                using (var cmd = new SqlCommand(queryString, connection))
                {
                    cmd.Parameters.Add("@Age", SqlDbType.Float).Value = sample.Age;
                    cmd.Parameters.Add("@Gender", SqlDbType.Float).Value = sample.Gender;
                    cmd.Parameters.Add("@Growth", SqlDbType.Float).Value = sample.Growth;
                    cmd.Parameters.Add("@Weight", SqlDbType.Float).Value = sample.Weight;
                    cmd.Parameters.Add("@BodyMassIndex", SqlDbType.Float).Value = sample.BodyMassIndex;
                    cmd.Parameters.Add("@Distance", SqlDbType.Float).Value = sample.Distance;
                    cmd.Parameters.Add("@SleepHoursCount", SqlDbType.Float).Value = sample.SleepHoursCount;
                    cmd.Parameters.Add("@SleepQuality", SqlDbType.Float).Value = sample.SleepQuality;
                    cmd.Parameters.Add("@SpentCalories", SqlDbType.Float).Value = sample.SpentCalories;
                    cmd.Parameters.Add("@EatenCalories", SqlDbType.Float).Value = sample.EatenCalories;
                    cmd.Parameters.Add("@FoodMultiplicity", SqlDbType.Float).Value = sample.FoodMultiplicity;
                    cmd.Parameters.Add("@FatAmount", SqlDbType.Float).Value = sample.FatAmount;
                    cmd.Parameters.Add("@CarbohydrateAmount", SqlDbType.Float).Value = sample.CarbohydrateAmount;
                    cmd.Parameters.Add("@ProteinAmount", SqlDbType.Float).Value = sample.ProteinAmount;
                    cmd.Parameters.Add("@VitaminC", SqlDbType.Float).Value = sample.VitaminC;
                    cmd.Parameters.Add("@SugarLevel", SqlDbType.Float).Value = sample.SugarLevel;
                    cmd.Parameters.Add("@StressLevel", SqlDbType.Float).Value = sample.StressLevel;
                    cmd.Parameters.Add("@Temperature", SqlDbType.Float).Value = sample.Temperature;
                    cmd.Parameters.Add("@HightPressure", SqlDbType.Float).Value = sample.HightPressure;
                    cmd.Parameters.Add("@LowPressure", SqlDbType.Float).Value = sample.LowPressure;
                    cmd.Parameters.Add("@Pulse", SqlDbType.Float).Value = sample.Pulse;
                    cmd.Parameters.Add("@DiseaseStatusID", SqlDbType.Int).Value = (int) sample.Disease.Status;
                    cmd.Parameters.Add("@GroupRiskID", SqlDbType.Int).Value = (int) sample.GroupRisk.GroupRiskType;

                    connection.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        }

        private void DeleteSample(DiseaseMonitoringSample sample)
        {
            var queryString = "DELETE FROM LearningSamples WHERE LearningSamples.SampleID = " + sample.SampleID;
            using (var connection = new SqlConnection(_connectionString))
            {
                using (var cmd = new SqlCommand(queryString, connection))
                {
                    connection.Open();
                    cmd.ExecuteNonQuery();
                }
            }
        } 
    }
}
