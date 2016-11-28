using NeuralNetworkDataStorageBLL.Enums;

namespace NeuralNetworkSystemAPI.Models
{
    public class DiseaseLearningSampleModel : DieseaseRequestModel
    {
        public RiskStatusEnum GroupRiskStatus { get; set; }
        public DiseasesStatusEnum DiseaseStatus { get; set; }
    }
}