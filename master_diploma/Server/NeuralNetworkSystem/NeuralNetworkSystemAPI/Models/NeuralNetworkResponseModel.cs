using System.Collections.Generic;
using NeuralNetworkDataStorageBLL.DTO;

namespace NeuralNetworkSystemAPI.Models
{
    public class NeuralNetworkResponse
    {
        public NeuralNetworkResponse()
        {
            Suggestions = new List<Suggestion>();
        }

        public int Disease { get; set; }
        public string DiseaseDescription { get; set; }

        public int GroupRisk { get; set; }
        public string GroupRiskDescription { get; set; }

        public List<Suggestion> Suggestions { get; set; }
    }
}