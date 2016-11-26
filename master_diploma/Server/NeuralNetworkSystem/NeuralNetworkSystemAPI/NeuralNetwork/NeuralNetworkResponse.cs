using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NeuralNetworkSystemAPI.NeuralNetwork
{
    public class NeuralNetworkResponse
    {
        public double FirstOutput { get; set; }
        public double SecondOuput { get; set; }
        public  int Diseases { get; set; }
        public  string Description { get; set; }
    }
}