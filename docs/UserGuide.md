---
layout: page
title: User Guide
---

# **Welcome to the NeuroSync User Guide!**

NeuroSync helps psychiatrists **manage patient records and session notes efficiently**. Choose a topic below to get step-by-step instructions, troubleshoot issues, and maximize your productivity!

---

## 🔍 **Search the User Guide**

<input type="text" id="search-box" placeholder="Search this guide..." onkeyup="searchFunction()">
<div id="search-dropdown" style="display:none; position:absolute; background:white; border:1px solid #ccc; width:300px; max-height:200px; overflow:auto;"></div>

<script>
function searchFunction() {
    var input = document.getElementById("search-box");
    var filter = input.value.toUpperCase();
    var dropdown = document.getElementById("search-dropdown");
    dropdown.innerHTML = "";
    dropdown.style.display = filter.length > 0 ? "block" : "none";

    // Select only the links in the Table of Contents
    var tocLinks = document.querySelectorAll("a[href^='#']");

    tocLinks.forEach(function(link) {
        var txtValue = link.textContent || link.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            var div = document.createElement("div");
            div.innerHTML = '<a href="' + link.getAttribute('href') + '" style="display:block; padding:5px; text-decoration:none; color:black;">' + txtValue.substring(0, 50) + '...</a>';
            div.style.borderBottom = "1px solid #ddd";
            div.style.padding = "5px";
            div.style.cursor = "pointer";
            dropdown.appendChild(div);
        }
    });
}
</script>

---

## 📜 Table of Contents {#table-of-contents}

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Core Features](#core-features)
4. [Command Summary](#command-summary)
5. [Commands & Usage](#commands--usage)
   - [View All Patients](#listing-patients)
   - [Finding a Patient](#finding-a-patient)
   - [Adding a Patient](#adding-a-patient)
   - [Editing a Patient](#editing-a-patient)
   - [Deleting a Patient](#deleting-a-patient)
   - [Viewing Notes](#viewing-notes)
   - [Filtering Notes](#filtering-notes)
   - [Adding a Note](#adding-a-note)
   - [Edit a Note](#editing-a-note)
   - [Delete a Note](#deleting-a-note)
   - [Clear All Data](#clear-data)
   - [Help](#help)
6. [FAQs & Troubleshooting](#faqs--troubleshooting)
7. [Technology Stack](#technology-stack)
8. [Acknowledgemets](#acknowledgements)
9. [Contact & Support](#contact-support)

---

## 📢 **Introduction** {#introduction}

### What does NeuroSync do?

NeuroSync **keeps track of patient details and session notes effortlessly**. No more searching through paper files—find what you need **instantly** using powerful search and filter commands.

**Why Use NeuroSync?**
✅ **Fast & Simple** - No unnecessary clicks, just type commands.
✅ **Powerful Search & Filters** - Retrieve patient data in seconds.
✅ **Organized & Secure** - Keep confidential data well-structured.

[🔝 Back to Top](#table-of-contents)

---

## 🚀 **Getting Started** {#getting-started}

### **1️⃣ Download & Install**

1. **Download** the latest release from [NeuroSync Releases](https://github.com/AY2425S2-CS2103T-F13-1/tp/releases).
2. Place the `NeuroSync.jar` file in your preferred directory.
3. Open **Terminal/Command Prompt**.
4. Navigate to the folder containing `NeuroSync.jar`.
5. Run the app using:
   ```bash
   java -jar NeuroSync.jar
   ```

### **2️⃣ System Requirements**

- **Java**: Version 17 or later
- **OS Compatibility**: Windows, macOS, Linux

[🔝 Back to Top](#table-of-contents)

---

## 💡 **Core Features** {#core-features}

- 📁 **Manage Patient Records** - Add, edit, and delete patient details.
- 📝 **Session Notes** - Keep track of discussions, diagnoses, and treatments.
- 🔎 **Powerful Search & Filters** - Quickly retrieve any patient or note.
- ⚡ **Command-Based Interface** - Fast, efficient, and easy to use.

[🔝 Back to Top](#table-of-contents)

---

## 🧭 **Command Summary** {#command-summary}

| **Action**         | **Command Syntax**                                                                        |
| ------------------ | ----------------------------------------------------------------------------------------- |
| **List Patients**  | `list`                                                                                    |
| **Find Patient**   | `find KEYWORD [MORE_KEYWORDS]`                                                            |
| **Add Patient**    | `add n/NAME p/PHONE_NUMBER a/ADDRESS [t/TAG]…​`                                   |
| **Edit Patient**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]…​`                    |
| **Delete Patient** | `delete INDEX`                                                                            |
| -----------------  | ----------------------------------------------------------------------------------------- |
| **View Notes**     | `viewnotes INDEX or viewnotes all`                                                        |
| **Filter Note**    | `filternote INDEX nt/TITLE_KEYWORD`                                                       |
| **Add Note**       | `note INDEX nt/TITLE nc/CONTENT`                                                          |
| **Edit Note**      | `editnote INDEX nt/TITLE nc/CONTENT`                                                      |
| **Delete Note**    | `deletenote INDEX nt/TITLE`                                                               |
| -----------------  | ----------------------------------------------------------------------------------------- |
| **Clear Data**     | `clear`                                                                                   |
| **Help**           | `help`                                                                                    |

[🔝 Back to Top](#table-of-contents)

---

## 🎯 **Commands & Usage** {#commands--usage}

Each command is designed to make patient and session management **fast and intuitive**.

### **Listing Patients** {#listing-patients}

```bash
list
```

**Example:**

```bash
list
```

✔️ **Expected Output:**

```
1. John Doe (Phone: 98005442)
2. Jane Smith (Phone: 91234567)
```

⚠️ **Warning:** This will show all patients, so be mindful if you have a large list.<br>
💡 **Tip:** Use the **Find** command to quickly locate a patient.

[🔝 Back to Top](#table-of-contents)

---

### **Finding a Patient** {#finding-a-patient}

```bash
find KEYWORD [MORE_KEYWORDS]
```

**Example:**

```bash
find John
```

✔️ **Expected Output:**

```
1. John Doe (Phone: 98005442)
```

⚠️ **Warning:** Multiple matches may be returned if you use a common name.<br>
💡 **Tip:** Use specific names or IDs to narrow your search.

[🔝 Back to Top](#table-of-contents)

---

### **Adding a Patient** {#adding-a-patient}

```bash
add n/NAME p/PHONE_NUMBER a/ADDRESS [t/TAG]…​
```

**Example:**

```bash
add n/John Doe p/98005442 a/123 Clementi Street t/ADHD
```

✔️ **Expected Output:**

```
Patient added: John Doe (Phone: 98005442)
```

⚠️ **Warning:** Avoid duplicate names for clarity.<br>
💡 **Tip:** Use **tags** (`t/`) to categorize patients (e.g., `t/Anxiety`).

[🔝 Back to Top](#table-of-contents)

---

### **Editing a Patient** {#editing-a-patient}

```bash
edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [t/TAG]…​
```

**Example:**

```bash
edit 1 p/91234567
```

✔️ **Expected Output:**

```
Updated patient: John Doe (Phone: 91234567)
```

⚠️ **Warning:** Only update fields that need changing to avoid accidental edits.<br>
💡 **Tip:** You can edit just one piece of data, like only the phone number.

[🔝 Back to Top](#table-of-contents)

---

### **Deleting a Patient** {#deleting-a-patient}

```bash
delete INDEX
```

**Example:**

```bash
delete 2
```

✔️ **Expected Output:**

```
Deleted patient at index 2.
```

⚠️ **Warning:** This action is **irreversible**. Double-check before deleting.<br>
💡 **Tip:** Be careful when using this command, especially with large patient lists.

[🔝 Back to Top](#table-of-contents)

---

### **Viewing Notes** {#viewing-notes}

```bash
viewnotes INDEX
viewnotes all    # View notes for all patients
```

**Example:**

```bash
viewnotes 1
```

✔️ **Expected Output:**

```
1. Mood Assessment - Discussed mood swings.
2. Sleep Patterns - Irregular sleep schedule.
```

⚠️ **Warning:** If a patient has many notes, it may take a moment to load.<br>
💡 **Tip:** Use **Filter Notes** to narrow down results if you have many notes.

[🔝 Back to Top](#table-of-contents)

---

### **Filtering Notes** {#filtering-notes}

```bash
filternote INDEX nt/TITLE_KEYWORD
```

**Example:**

```bash
filternote 1 nt/anxiety
```

✔️ **Expected Output:**

```
Showing notes for Patient 1 with keyword: "anxiety"
```

⚠️ **Warning:** Be specific with keywords to avoid too many results.<br>
💡 **Tip:** Keywords should be part of the note's title for better results.

[🔝 Back to Top](#table-of-contents)

---

### **Adding a Note** {#adding-a-note}

```bash
note INDEX nt/TITLE nc/CONTENT
```

**Example:**

```bash
note 1 nt/Mood Assessment nc/Discussed mood swings and coping strategies.
```

✔️ **Expected Output:**

```
Note added for Patient 1: "Mood Assessment"
```

⚠️ **Warning:** Ensure the title is relevant and specific to the session.<br>
💡 **Tip:** Keep notes short and concise, highlighting the most important aspects.

[🔝 Back to Top](#table-of-contents)

---

### **Editing a Note** {#editing-a-note}

```bash
editnote INDEX nt/TITLE nc/CONTENT
```

**Example:**

```bash
editnote 1 nt/Mood Assessment nc/Updated content with more details.
```

✔️ **Expected Output:**

```
Note updated for Patient 1: "Mood Assessment"
```

⚠️ **Warning:** Changes are permanent once saved, so be sure the content is correct.<br>
💡 **Tip:** Edit only the necessary fields without altering other details.

[🔝 Back to Top](#table-of-contents)

---

### **Deleting a Note** {#deleting-a-note}

```bash
deletenote INDEX nt/TITLE
```

**Example:**

```bash
deletenote 1 nt/Mood Assessment
```

✔️ **Expected Output:**

```
Deleted note: "Mood Assessment" for Patient 1.
```

⚠️ **Warning:** **Deleted notes cannot be recovered.**<br>
💡 **Tip:** Keep backups if needed.

[🔝 Back to Top](#table-of-contents)

---

### **Clear All Data** {#clear-data}

```bash
clear
```

**Example:**

```bash
clear
```

✔️ **Expected Output:**

```
All patient and note data cleared.
```

⚠️ **Warning:** This is a **permanent** action. Use with caution.<br>
💡 **Tip:** Regularly back up your data before using this command.

[🔝 Back to Top](#table-of-contents)

---

### **Help** {#help}

```bash
help
```

**Example:**

```bash
help
```

✔️ **Expected Output:**

```
List of available commands and syntax.
```

⚠️ **Warning:** This is just a list of commands, not a tutorial.<br>
💡 **Tip:** Use **help** if you forget a command or need to refer to syntax quickly.

[🔝 Back to Top](#table-of-contents)

---

## ❓ **FAQs & Troubleshooting** {#faqs--troubleshooting}

**Q: Where is my data stored?**
**A:** Your data is saved in `addressbook.json` inside the `/data` folder.

**Q: Can I undo a deletion?**
**A:** No. There is **no undo** feature, so backup your data frequently.

**Q: How do I transfer data to another device?**
**A:** Copy the `addressbook.json` file from `/data` and paste it into the same directory on the new device.

**Q: What happens when I run `clear`?**
**A:** It **deletes all data permanently**. Use it with caution.

[🔝 Back to Top](#table-of-contents)

---

## 🛠 Technology Stack {#technology-stack}

- Java 17
- JavaFX for UI
- Gradle for build automation

[🔝 Back to Top](#table-of-contents)

---

## 🙌 Acknowledgements {#acknowledgements}

This project is based on the AddressBook-Level3 project by the SE-EDU initiative.

Visit [se-education.org](https://se-education.org) for more open-source CS education projects.

[🔝 Back to Top](#table-of-contents)

---

## 📬 Contact & Support {#contact-support}

For issues and contributions, please raise an issue or pull request in the [GitHub repository](https://github.com/AY2425S2-CS2103T-F13-1/tp).

[🔝 Back to Top](#table-of-contents)

🚀 _Enjoy using NeuroSync!_
