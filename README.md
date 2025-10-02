# Time Attendance System (Spring Boot + MySQL)

โปรเจคระบบ Time Attendance พัฒนาโดยใช้ **Spring Boot (Java 17)**, **Maven**, **Thymeleaf** และ **MySQL**  
ไฟล์ตัวอย่างฐานข้อมูลเก็บไว้ที่ `db/time_attendance.sql` เพื่อให้เพื่อน clone แล้วได้ environment เหมือนกันกับที่เครื่องผู้พัฒนา

---

# เนื้อหาเอกสารนี้ครอบคลุม
- ข้อกำหนด (Requirements)
- โครงสร้างโปรเจค (Project structure)
- วิธีตั้งค่าและรัน (Step-by-step setup)
  - สร้าง/นำเข้าฐานข้อมูล
  - ตั้งค่าไฟล์ `application.properties`
  - รันโปรเจค (Eclipse / CLI)
- วิธี push ขึ้น GitHub (command line)
- วิธี clone และ import เข้า Eclipse สำหรับเพื่อนที่รับโปรเจคไปต่อ
- .gitignore ที่แนะนำ
- Troubleshooting (ข้อผิดพลาดที่พบบ่อย)
- ข้อแนะนำด้านความปลอดภัย

---

## ⚙️ Requirements (สิ่งที่ต้องติดตั้งก่อน)
- Java 17 (JDK 17)
  - ตรวจสอบด้วย `java -version`
- Maven (3.6+)  
  - ตรวจสอบด้วย `mvn -v`
- MySQL Server (แนะนำ MySQL 8)
- MySQL Workbench หรือ mysql client (optional)
- Git
- Eclipse IDE for Enterprise Java Developers (หรือ IDE อื่นที่รองรับ Maven)
- (ถ้าใช้ HTTPS กับ GitHub) Personal Access Token (PAT) ของ GitHub สำหรับ auth แทนรหัสผ่าน

---

## 📂 โครงสร้างโปรเจค (ตัวอย่าง)
