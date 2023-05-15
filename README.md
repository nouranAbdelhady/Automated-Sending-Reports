# Automation for Sending Reports
 
This project aims to automate the process of sending reports via WhatsApp using Java and Selenium.

# Prerequisites
Download and install the appropriate Selenium WebDriver for your browser.
Locate the WebDriver executable for your browser and update the project accordingly if necessary.

# Setup
1. Clone or download this repository.
2. Place the PDF files you wish to send in the "Files" folder. Make sure the file names match the Full Name in the Excel sheet.
3. Update the Sheet.xlsx Excel file with the following information:
   - Phone number: Specify the recipient's phone number to send the PDF and message to.
   - Full name: This should match the PDF file name.
   - URL link: Generate the URL link using the appropriate helper function in the Excel sheet.

# Usage
1. Open the project in your preferred Java IDE.
2. Update the path to the WebDriver executable in the code if necessary.
3. Run the 'Reports.java' file to initiate the automation process.
4. The program will read the data from the Excel sheet, open a WhatsApp chat with the specified phone number, and send the corresponding PDF file.

# Note
- Make sure you have a stable internet connection during the execution of the program.
- You do **not** have WhatsApp App installed on your device.

--------------------------------------------

Please refer to the provided Sheet.xlsx file for guidance on how to structure the data.

Feel free to reach out for any assistance or inquiries. Happy automating :)!