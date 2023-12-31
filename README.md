## Spring Boot

Cấu trúc source code của Spring Boot được dựa trên hai mô hình là mô hình MVC và mô hình 3 lớp.

### 1. Mô hình ba lớp (three tier) (https://www.ibm.com/topics/three-tier-architecture)
Đây là mô hình tổ chức source code rất phổ biến trong Spring Boot. 
Cụ thể, ứng dụng được chia làm 3 tầng (tier hoặc layer) như sau:

- Presentation layer: tầng này tương tác với người dùng, bằng View, Controller (trong MVC) hoặc API (nếu có).
- Business logic layer: Chứa toàn bộ logic của chương trình, các đa số code nằm ở đây
- Data access layer: Tương tác với database, trả về kết quả cho tầng business logic

Trong Spring Boot, thì có một số thành phần đại diện cho từng lớp:

- Service: chứa các business logic code
- Repository: đại diện cho tầng data access

Còn presentation layer thì chúng ta sẽ bàn tiếp qua mô hình MVC.

### 2. Mô hình MVC
Do Spring Boot chỉ là wrapper cho Spring, chúng ta vẫn sử dụng ngầm các module Spring khác bên dưới, 
ví dụ như Spring MVC.

Cụ thể, nó chia tầng presentation làm 3 phần:
- Model: các cấu trúc dữ liệu của toàn chương trình, có thể đại diện cho trạng thái của ứng dụng
- View: lớp giao diện, dùng để hiển thị dữ liệu ra cho user xem và tương tác
- Controller: kết nối giữa Model và View, điều khiển dòng dữ liệu

Dữ liệu từ Model qua Controller sau đó được gửi cho View hiển thị ra.
Và ngược lại, khi có yêu cầu mới từ View, thì sẽ qua Controller thực hiện thay đổi dữ liệu của Model.

### 3. Các thành phần quan trọng

Kết hợp hai mô hình lại, chúng ta có được ứng dụng Spring Boot hoàn chỉnh, gồm các thành phần sau:

- Controller: trả về View (có chứa data sẵn, dạng trang HTML), hoặc Model thể hiện dưới dạng API cho View (View viết riêng bằng React, Vue, hoặc Angular).
- Service: chứa các code tính toán, xử lý. Khi Controller yêu cầu, thì Service tương ứng sẽ tiếp nhận và cho ra dữ liệu trả cho Controller (trả về Model). Controller sẽ gửi về View như trên.
- Repository: Service còn có thể tương tác với service khác, hoặc dùng Repository để gọi DB. Repository là file trực tiếp tương tác, đọc ghi dữ liệu trong DB và trả cho service.
- Model chỉ đơn giản là các đối tượng được Service tính toán xong trả về cho Controller.
- View thì có 2 loại, một là dạng truyền thống là trả về 1 cục HTML có data rồi. Lúc này Controller sẽ pass dữ liệu vào View và return về (Spring MVC có JSP hoặc template engine như Thymeleaf làm điều đó).
- View dạng 2 là dạng View tách riêng (không thuộc về project Spring boot). Thường có trong các hệ thống dùng API. View sẽ được viết riêng bằng React, Angular,... Controller sẽ đưa dữ liệu Model thông qua API cho View, và cũng nhận lại các yêu cầu qua API luôn.

### Sơ đồ luồng đi

Để hiểu rõ hơn về các thành phần trong Spring Boot, chúng ta hãy xem qua sơ đồ luồng đi và sự tương tác giữa chúng. 

![](../spring-project/68d13e98-8714-4dd9-ae27-641ee729ab20.png)


- Đầu tiên, user sẽ vào View để xem, tương tác
- Khi user bắt đầu load dữ liệu (ví dụ click nút Reload), thì 1 request từ View gửi cho Controller 
- Controller nhận được yêu cầu, bắt đầu request đến Service (trong code là gọi method của Service)
- Service nhận được yêu cầu từ Controller, đối với các code đơn giản có thể tính toán và trả về luôn. Nhưng các thao tác cần đụng tới database thì Service phải gọi Repository để lấy dữ liệu trong DB
- Repository nhận được yêu cầu từ Service, sẽ thao tác với DB. Data lấy ra trong DB được hệ thống ORM (như JPA hoặc Hibernate) mapping thành các object (trong Java). Các object này gọi là Entity.

Bây giờ sẽ là đi ngược lại trả về cho user:

- Service nhận các Entity được Repository trả về, biến đổi nó. Biến đổi ở đây là có thể thực hiện tính toán, thêm bớt các field,... và cuối cùng biến Entity thành Model. Model sẽ được trả lại cho Controller.
- Controller nhận được Model, nó sẽ return cho View. Có 2 cách, một là dùng template engine pass dữ liệu Model vào trang HTML, rồi trả về cục HTML (đã có data) cho client. Cách 2 là gửi qua API, View tự parse ra và hiển thị tương ứng (hiển thị thế nào tùy View).
- Khi View hiển thị xong, user sẽ thấy danh sách user hiện lên trang web.

Note:
- Giữ cho Controller càng ít code càng tốt. Vì Controller chỉ là trung gian kết nối thông, nên không nên chứa nhiều code, thay vào đó nên bỏ vào Service.
- Nên tách bạch Service rõ ràng. Không nên cho 1 service thực hiện nhiều công việc, nên tách ra nhiều Service.
