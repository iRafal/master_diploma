using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace JustForChartProject
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void BuildButton_Click(object sender, EventArgs e)
        {
            var path = FilePath.Text;
            string value;
            var resultList = new List<double>();

            var file = new System.IO.StreamReader(path);

            while ((value = file.ReadLine()) != null)
            {
                resultList.Add(double.Parse(value));
            }

            file.Close();

            chart1.Series.Clear();
            var series1 = new Series
            {
                Name = "Series1",
                Color = System.Drawing.Color.Aqua,
                IsVisibleInLegend = false,
                IsXValueIndexed = true,
                ChartType = SeriesChartType.Line
            };

            chart1.Series.Add(series1);

            for (int i = 0; i < resultList.Count(); i++)
            {
                series1.Points.AddXY(i, resultList[i]);
            }

            chart1.Invalidate();
        }

        private void FilePath_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
