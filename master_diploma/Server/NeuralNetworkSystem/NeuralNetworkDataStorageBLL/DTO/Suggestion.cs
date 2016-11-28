using System.Xml.Serialization;
using Newtonsoft.Json;

namespace NeuralNetworkDataStorageBLL.DTO
{
    public class Suggestion
    {
        [JsonIgnore,XmlIgnore]       
        public int SuggestionID { get; set; }
        public string Description { get; set; }
        [JsonIgnore, XmlIgnore]
        public Disease Disease { get; set; }
        public Parameter Parameter { get; set; }
    }
}
