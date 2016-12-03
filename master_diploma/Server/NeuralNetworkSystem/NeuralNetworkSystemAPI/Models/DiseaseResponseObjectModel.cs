using System.Collections.Generic;
using NeuralNetworkDataStorageBLL.DTO;

namespace NeuralNetworkSystemAPI.Models
{
    public class DiseaseResponseObjectModel
    {
        public DiseaseResponseObjectModel()
        {
            Suggestions = new List<Suggestion>();
        }

        public Disease Disease { get; set; }
        public GroupRisk GroupRisk { get; set; }
        public List<Suggestion> Suggestions { get; set; }
    }
}