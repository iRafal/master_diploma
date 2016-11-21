using System;

namespace NeuralNetworkSystemBLL.Attributes
{
    [AttributeUsage(AttributeTargets.Property)]
    public class InputTrainingSampleAttribute : Attribute
    {
        public int InputNumber { get; set; }

        public InputTrainingSampleAttribute(int inputNumber)
        {
            InputNumber = inputNumber;
        }
    }
}
