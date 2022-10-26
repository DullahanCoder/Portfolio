using System;
using System.Collections.Generic;
using System.Drawing;
using System.Media;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        private List<string> wakeUpTimesList = new List<string>();
        private bool ringing = false;

        public Form1()
        {
            InitializeComponent();

            Time.Text = GetTime();
            timePicker.ShowUpDown = true;
            timePicker.CustomFormat = "HH:mm";
            timePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
        }

        private string GetTime()
        {
            return DateTime.Now.ToString("HH:mm");
        }

        private void Ring()
        {
            ringing = true;
            SoundPlayer ringTone = new SoundPlayer(TimeAlarm.Properties.Resources.AlarmVoice);
            ringTone.Play();
        }

        private void closeButton_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            Time.Text = GetTime();

            if (wakeUpTimesList.Count == 0)
            {
                actualTimeLabel.BackColor =  Time.BackColor = Color.Yellow;
                toolTip1.Active = true;
            }
            else
            {
                actualTimeLabel.BackColor = Time.BackColor = Color.Transparent;
                toolTip1.Active = false;

                string actualTime = GetTime();
                if (wakeUpTimesList.Contains(actualTime))
                {
                    if (!ringing)
                    {
                        Ring();
                        RemoveFromList(actualTime);
                    }
                }
                else
                {
                    ringing = false;
                }
            }
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            string time = timePicker.Value.ToString("HH:mm");
            AddToList(time);
        }

        private void AddToList(string time)
        {
            if (!wakeUpTimesList.Contains(time))
            {
                wakeUpTimesList.Add(time);
                UpdateLists();
                timeSelector.Items.Add(time);
            }
        }

        private void UpdateLists()
        {
            string listText = "";

            foreach (string time in wakeUpTimesList)
            {
                listText += time + "\n";
            }

            timeList.Text = listText;
        }

        private void deleteButton_Click(object sender, EventArgs e)
        {
            int index = timeSelector.SelectedIndex;
            RemoveFromList(index);
        }

        private void RemoveFromList(int index)
        {
            if (index == -1)
            {
                return;
            }

            wakeUpTimesList.RemoveAt(index);
            timeSelector.Items.RemoveAt(index);
            UpdateLists();
            timeSelector.ResetText();
        }

        private void RemoveFromList(string time)
        {
            int index = wakeUpTimesList.IndexOf(time);
            RemoveFromList(index);
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
