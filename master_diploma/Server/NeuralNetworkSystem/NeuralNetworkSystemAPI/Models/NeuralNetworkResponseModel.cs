using System.Collections.Generic;

namespace NeuralNetworkSystemAPI.Models
{
    public class NeuralNetworkResponse
    {
        public NeuralNetworkResponse()
        {
            Forecasts = new List<DiseaseResponseObjectModel>();
        }

        public List<DiseaseResponseObjectModel> Forecasts { get; set; }
    }
}