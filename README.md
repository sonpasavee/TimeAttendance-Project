# ⏱️ Time Attendance System (Spring Boot + MySQL)

โปรเจคนี้เป็นระบบ **บันทึกเวลาเข้า-ออกงาน (Time Attendance)**  
พัฒนาด้วย **Spring Boot (Java 17)** + **Thymeleaf** + **MySQL**  
มีระบบ Login / Register และการจัดการเวลาทำงานสำหรับพนักงาน  

---

## 📖 คุณสมบัติ (Features)
- ระบบลงเวลาเข้า-ออกงาน
- ระบบผู้ใช้งาน (Login / Register)
- ใช้ฐานข้อมูล MySQL
- ทำงานบน Spring Boot + Maven
- เทมเพลต UI ใช้ Thymeleaf

---

## ⚙️ Requirements
ก่อนที่จะใช้งานโปรเจคนี้ ให้ติดตั้งเครื่องมือดังต่อไปนี้:

- [Java JDK 17](https://adoptium.net/) หรือสูงกว่า
- [Maven 3.6+](https://maven.apache.org/)
- [MySQL Server 8+](https://dev.mysql.com/downloads/)
- [Eclipse IDE for Enterprise Java Developers](https://www.eclipse.org/downloads/)
- [Git](https://git-scm.com/)

---

## 📂 โครงสร้างโปรเจค (Project Structure)
project-pyp/
├─ src/main/java/com/cp/project/... # Source Code
├─ src/main/resources/ # Static, Templates, Config
│ ├─ static/
│ ├─ templates/
│ └─ application.properties.example
├─ db/
│ └─ time_attendance.sql # Database Schema & Data
├─ pom.xml # Maven Dependencies
└─ README.md

---

## 🚀 วิธีการติดตั้งและรัน (Setup & Run)

### 1. Clone โปรเจคจาก GitHub
เปิด Terminal แล้วรัน:
```bash
git clone https://github.com/<USERNAME>/<REPO>.git
cd <REPO>
2. Import โปรเจคเข้า Eclipse

เปิด Eclipse

ไปที่ File > Import...

เลือก Existing Maven Projects

Browse ไปยังโฟลเดอร์ที่ clone มา

เลือกโปรเจค → Finish
3. สร้างฐานข้อมูล MySQL

เปิด MySQL Workbench หรือ MySQL CLI

สร้างฐานข้อมูลชื่อ time_attendance
CREATE DATABASE time_attendance
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
Import ไฟล์ SQL ที่อยู่ในโฟลเดอร์ db/time_attendance.sql

Workbench: ไปที่ Server > Data Import → เลือกไฟล์ db/time_attendance.sql → Start Import
mysql -u root -p time_attendance < db/time_attendance.sql
4. ตั้งค่าไฟล์ application.properties

ไปที่ src/main/resources/

คัดลอกไฟล์ตัวอย่าง:cp application.properties.example application.properties
