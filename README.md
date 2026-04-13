1. Tạo nhánh mới
   Để tạo một nhánh mới (ví dụ tên nhánh là feature-symmetric):
   git branch feature-symmetric

2. Chuyển sang nhánh vừa tạo
   Để bắt đầu làm việc trên nhánh đó, bạn cần chuyển từ nhánh main sang:
   git checkout feature-symmetric

Mẹo: Bạn có thể kết hợp cả 2 bước trên (vừa tạo vừa chuyển) bằng một câu lệnh duy nhất:
git checkout -b feature-symmetric

3. Xem danh sách các nhánh
   Để kiểm tra xem hiện tại đang có bao nhiêu nhánh và mình đang ở nhánh nào:
   git branch

4. Đẩy nhánh lên GitHub
   Sau khi bạn đã code xong ở máy cục bộ và muốn đưa nhánh đó lên để giảng viên thấy lịch sử commit:
   git push origin feature-symmetric

// Không làm cái này 5. Hợp nhất nhánh (Merge)
Khi tính năng đã chạy ổn định và bạn muốn đưa nó vào nhánh chính (main):

Chuyển về nhánh chính: git checkout main

Gộp nhánh tính năng vào: git merge feature-symmetric
