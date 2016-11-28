using System.Collections.Generic;

namespace NeuralNetworkSystemBLL.Interfaces.Learning
{
    public interface ILearningSamplesRepository
    {
        List<ILearningSample> LearningSamples { get; set; }
        ILearningSamplesRepository PopulateSamples();
    }
}
