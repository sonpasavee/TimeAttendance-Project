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
```
project-pyp/
├─ src/main/java/com/cp/project/...   # Source Code
├─ src/main/resources/                # Static, Templates, Config
│   ├─ static/
│   ├─ templates/
│   └─ application.properties.example
├─ db/
│   └─ time_attendance.sql            # Database Schema & Data
├─ pom.xml                            # Maven Dependencies
└─ README.md
```

---

## 🚀 วิธีการติดตั้งและรัน (Setup & Run)

### 1. Clone โปรเจคจาก GitHub
เปิด Terminal แล้วรัน:
```bash
git clone https://github.com/<USERNAME>/<REPO>.git
cd <REPO>
```

---

### 2. Import โปรเจคเข้า Eclipse
1. เปิด **Eclipse**
2. ไปที่ `File > Import...`
3. เลือก `Existing Maven Projects`
4. Browse ไปยังโฟลเดอร์ที่ clone มา
5. เลือกโปรเจค → Finish

---

### 3. สร้างฐานข้อมูล MySQL
1. เปิด **MySQL Workbench** หรือ MySQL CLI  
2. สร้างฐานข้อมูลชื่อ `time_attendance`
   ```sql
   CREATE DATABASE time_attendance
     CHARACTER SET utf8mb4
     COLLATE utf8mb4_unicode_ci;
   ```
3. Import ไฟล์ SQL ที่อยู่ในโฟลเดอร์ `db/time_attendance.sql`
   - Workbench: ไปที่ `Server > Data Import` → เลือกไฟล์ `db/time_attendance.sql` → Start Import  
   - CLI:
     ```bash
     mysql -u root -p time_attendance < db/time_attendance.sql
     ```

---

### 4. ตั้งค่าไฟล์ `application.properties`
1. ไปที่ `src/main/resources/`
2. คัดลอกไฟล์ตัวอย่าง:
   ```bash
   cp application.properties.example application.properties
   ```
3. แก้ค่าให้ตรงกับ MySQL ของเพื่อน:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/time_attendance?useSSL=false&serverTimezone=Asia/Bangkok
   spring.datasource.username=root
   spring.datasource.password=YOUR_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

---

### 5. รันโปรเจค
**จาก Eclipse**
- คลิกขวาที่โปรเจค → `Run As` → `Spring Boot App`

**หรือจาก Command Line**
```bash
mvn spring-boot:run
```

---

### 6. เปิดใช้งาน
เปิดเบราว์เซอร์แล้วเข้า:
```
http://localhost:8080
```

---

## 👩‍💻 วิธีการทำงานร่วมกัน (Collaborate)
### วิธีอัปเดตโค้ดใหม่จาก repo
```bash
git pull origin main
```

### วิธี push โค้ดขึ้น GitHub (สำหรับคนแก้ไขแล้ว)
```bash
git add .
git commit -m "อัปเดตฟีเจอร์ใหม่"
git push origin main
```

---

## 🛠 Troubleshooting
- **Error: Access denied for user** → ตรวจสอบ username/password ใน `application.properties`
- **Error: Port 8080 ถูกใช้งาน** → เปลี่ยน `server.port` ใน `application.properties`
- **Eclipse ไม่เห็น dependency** → `Right click project > Maven > Update Project...`

---

## 📌 หมายเหตุ
- ห้าม commit ไฟล์ `application.properties` ที่มีรหัสผ่านจริงลง GitHub
- ให้ commit เฉพาะ `application.properties.example` เพื่อให้เพื่อนแก้ไขเอง
- ถ้าใช้ GitHub Authentication ให้ใช้ **Personal Access Token (PAT)** แทน password

---

## 👥 Contributors
- [Your Name] (ผู้พัฒนาโปรเจคหลัก)
- เพื่อนที่ clone repo และช่วยพัฒนา

---
