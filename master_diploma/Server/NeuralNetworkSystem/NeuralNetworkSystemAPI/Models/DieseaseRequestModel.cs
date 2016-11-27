using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NeuralNetworkSystemAPI.Models
{
    public class DieseaseRequestModel
    {
        public double Age { get; set; }

        public double Gender { get; set; }

        public double Growth { get; set; }

        public double Weight { get; set; }

        public double BodyMassIndex { get; set; }

        public double Distance { get; set; }

        public double SleepHoursCount { get; set; }

        public double SleepQuality { get; set; }

        public double SpentCalories { get; set; }

        public double EatenCalories { get; set; }

        public double FoodMultiplicity { get; set; }

        public double FatAmount { get; set; }

        public double CarbohydrateAmount { get; set; }

        public double ProteinAmount { get; set; }

        public double VitaminC { get; set; }

        public double SugarLevel { get; set; }

        public double StressLevel { get; set; }

        public double Temperature { get; set; }

        public double HightPressure { get; set; }

        public double LowPressure { get; set; }

        public double Pulse { get; set; }
    }
}