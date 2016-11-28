using System.Xml.Serialization;
using Newtonsoft.Json;

namespace NeuralNetworkDataStorageBLL.DTO
{
    public class Parameter
    {
        [JsonIgnore,XmlIgnore]
        public int ParameterID { get; set; }
        public string Description { get; set; }
    }
}
