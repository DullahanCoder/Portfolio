
namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// Variabile di progettazione necessaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Pulire le risorse in uso.
        /// </summary>
        /// <param name="disposing">ha valore true se le risorse gestite devono essere eliminate, false in caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Codice generato da Progettazione Windows Form

        /// <summary>
        /// Metodo necessario per il supporto della finestra di progettazione. Non modificare
        /// il contenuto del metodo con l'editor di codice.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.timeList = new System.Windows.Forms.RichTextBox();
            this.timePicker = new System.Windows.Forms.DateTimePicker();
            this.actualTimeLabel = new System.Windows.Forms.Label();
            this.Time = new System.Windows.Forms.Label();
            this.closeButton = new System.Windows.Forms.Button();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.addButton = new System.Windows.Forms.Button();
            this.timeSelector = new System.Windows.Forms.ComboBox();
            this.deleteButton = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.timeList);
            this.groupBox1.Location = new System.Drawing.Point(9, 99);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(112, 140);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Orari";
            // 
            // timeList
            // 
            this.timeList.Enabled = false;
            this.timeList.Location = new System.Drawing.Point(7, 20);
            this.timeList.Name = "timeList";
            this.timeList.Size = new System.Drawing.Size(100, 114);
            this.timeList.TabIndex = 0;
            this.timeList.Text = "";
            // 
            // timePicker
            // 
            this.timePicker.AllowDrop = true;
            this.timePicker.Checked = false;
            this.timePicker.CustomFormat = "HH:mm";
            this.timePicker.DropDownAlign = System.Windows.Forms.LeftRightAlignment.Right;
            this.timePicker.Format = System.Windows.Forms.DateTimePickerFormat.Time;
            this.timePicker.Location = new System.Drawing.Point(134, 137);
            this.timePicker.Name = "timePicker";
            this.timePicker.Size = new System.Drawing.Size(52, 20);
            this.timePicker.TabIndex = 0;
            // 
            // actualTimeLabel
            // 
            this.actualTimeLabel.AutoSize = true;
            this.actualTimeLabel.Location = new System.Drawing.Point(99, 12);
            this.actualTimeLabel.Name = "actualTimeLabel";
            this.actualTimeLabel.Size = new System.Drawing.Size(60, 13);
            this.actualTimeLabel.TabIndex = 2;
            this.actualTimeLabel.Text = "Ora Attuale";
            this.toolTip1.SetToolTip(this.actualTimeLabel, "Nessun orario impostato");
            // 
            // Time
            // 
            this.Time.Font = new System.Drawing.Font("Microsoft Sans Serif", 39.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Time.Location = new System.Drawing.Point(52, 11);
            this.Time.Name = "Time";
            this.Time.Size = new System.Drawing.Size(162, 85);
            this.Time.TabIndex = 3;
            this.Time.Text = "22:00";
            this.Time.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.toolTip1.SetToolTip(this.Time, "Nessun orario impostato");
            // 
            // closeButton
            // 
            this.closeButton.AutoSize = true;
            this.closeButton.Location = new System.Drawing.Point(161, 204);
            this.closeButton.Name = "closeButton";
            this.closeButton.Size = new System.Drawing.Size(75, 23);
            this.closeButton.TabIndex = 4;
            this.closeButton.Text = "Chiudi";
            this.closeButton.UseVisualStyleBackColor = true;
            this.closeButton.Click += new System.EventHandler(this.closeButton_Click);
            // 
            // timer1
            // 
            this.timer1.Enabled = true;
            this.timer1.Interval = 1000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(190, 136);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(67, 23);
            this.addButton.TabIndex = 7;
            this.addButton.Text = "Aggiungi";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // timeSelector
            // 
            this.timeSelector.FormattingEnabled = true;
            this.timeSelector.Location = new System.Drawing.Point(134, 172);
            this.timeSelector.Name = "timeSelector";
            this.timeSelector.Size = new System.Drawing.Size(52, 21);
            this.timeSelector.TabIndex = 10;
            // 
            // deleteButton
            // 
            this.deleteButton.Location = new System.Drawing.Point(190, 171);
            this.deleteButton.Name = "deleteButton";
            this.deleteButton.Size = new System.Drawing.Size(67, 23);
            this.deleteButton.TabIndex = 11;
            this.deleteButton.Text = "Rimuovi";
            this.deleteButton.UseVisualStyleBackColor = true;
            this.deleteButton.Click += new System.EventHandler(this.deleteButton_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Location = new System.Drawing.Point(130, 99);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(130, 140);
            this.groupBox2.TabIndex = 12;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Editor Orari";
            // 
            // Form1
            // 
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
            this.ClientSize = new System.Drawing.Size(268, 245);
            this.Controls.Add(this.deleteButton);
            this.Controls.Add(this.timeSelector);
            this.Controls.Add(this.timePicker);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.closeButton);
            this.Controls.Add(this.actualTimeLabel);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.Time);
            this.Controls.Add(this.groupBox2);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(284, 284);
            this.MinimumSize = new System.Drawing.Size(284, 284);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Time Alarm";
            this.TopMost = true;
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label actualTimeLabel;
        private System.Windows.Forms.Label Time;
        private System.Windows.Forms.Button closeButton;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.DateTimePicker timePicker;
        private System.Windows.Forms.RichTextBox timeList;
        private System.Windows.Forms.ComboBox timeSelector;
        private System.Windows.Forms.Button deleteButton;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.ToolTip toolTip1;
    }
}

