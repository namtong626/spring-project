# Docker 
Docker là một nền tảng mở để phát triển, shipping và chạy các ứng dụng.

Docker cho phép bạn tách ứng dụng khỏi cơ sở hạ tầng để bạn có thể phân phối phần mềm một cách nhanh chóng. Với Docker, bạn có thể quản lý cơ sở hạ tầng của 
mình giống như cách bạn đang quản lý ứng dụng của mình.

Bằng cách tận dụng các phương pháp của Docker để shiping, testing và triển khai code một cách nhanh chóng, bạn có thể giảm đáng kể thời gian viết code và chạy ứng dun

​(https://docs.docker.com/get-docker/)

## Container

Container là một tiến trình được đóng gói và chạy trên máy chủ, tách biệt với tất cả các quy trình khác đang chạy trên máy chủ đó. 
Docker làm cho những tiến trình này trở nên dễ tiếp cận và dễ sử dụng.
- Là một phiên bản có thể chạy được của một image. Bạn có thể  create, start, stop, move, hoặc delete container bằng API Docker hoặc CLI.
- Có thể chạy trên máy local, máy ảo hoặc được triển khai trên cloud.
- Có tính di động (và có thể chạy trên mọi hệ điều hành).
- Được dùng riêng với các container khác và chạy phần mềm, binaries, config, v.v. của riêng nó.

## Image

Một container đang chạy sử dụng một hệ thống filesystem cô lập. Filesystem cô lập này được cung cấp bởi một image và image phải chứa mọi thứ cần thiết để chạy một ứng dụng
 - tất cả các dependencies, configurations, scripts, binaries, v.v. Image cũng chứa các cấu hình khác cho container, chẳng hạn như biến môi trường, default command để run, và metadata khác.

## Containerize an application

Trong phần hướng dẫn này, bạn sẽ làm việc với trình quản lý danh sách việc cần làm đơn giản chạy trên Node.js. Nếu bạn không quen với Node.js, đừng lo lắng. 
Hướng dẫn này không yêu cầu bất kỳ kinh nghiệm nào trước đó với JavaScript.

Clone this source:

```
git clone https://github.com/docker/getting-started-app.git
```

## Build the app's image

Để xây dựng Image, bạn sẽ cần sử dụng Dockerfile. Dockerfile chỉ đơn giản là một text-based file không có phần mở rộng tệp chứa các script hướng dẫn. 
Docker sử dụng tập lệnh này để xây dựng container image.


1. Trong thư mục Getting-started-app, cùng vị trí với file package.json tạo một file có tên Dockerfile.

```
type nul > Dockerfile
```

Hoặc

```
touch Dockerfile
```

2. Thêm các nội dung sau vào Dockerfile:

```
# syntax=docker/dockerfile:1

FROM node:18-alpine
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/index.js"]
EXPOSE 3000
```

3. Build the image 

```
 docker build -t getting-started .
```

Lệnh `docker build` sử dụng Dockerfile để xây dựng một image mới. Bạn có thể nhận thấy Docker đã tải xuống rất nhiều "layers". 
Điều này là do bạn đã hướng dẫn trình tạo rằng bạn muốn bắt đầu từ node:18-alpine image. Tuy nhiên, vì bạn không có nó trên máy nên Docker cần tải xuống image.

Sau khi Docker tải xuống image, các hướng dẫn từ Dockerfile sẽ được sao chép trong ứng dụng của bạn và sử dụng yarn để cài đặt các dependencies của ứng dụng.
Lệnh CMD chỉ định lệnh mặc định sẽ chạy khi khởi động container từ image này.

Cuối cùng,  `-t` gắn tên thẻ image của bạn. 

Dấu `.` ở cuối lệnh  docker build sẽ báo cho Docker biết rằng nó sẽ tìm Dockerfile trong thư mục hiện tại.
​


## Start an app container


Bây giờ bạn đã có Image, bạn có thể chạy ứng dụng trong vùng chứa bằng lệnh `docker run`.

1. Chạy container của bạn bằng lệnh docker run và chỉ định tên của image bạn vừa tạo:

```
docker run -dp 127.0.0.1:3000:3000 getting-started
```

- `-d` (viết tắt của --detach) chạy container ở chế độ nền. 

- `-p` (viết tắt của --publish) tạo mapping cổng giữa máy chủ và container. `-p` nhận giá trị chuỗi theo định dạng HOST:CONTAINER, trong đó HOST là địa chỉ trên máy chủ và 
CONTAINER là cổng trên container. Lệnh publishes cổng 3000 của container thành 127.0.0.1:3000 (localhost:3000) trên máy chủ. Nếu không có port mapping, bạn sẽ không thể truy cập
 ứng dụng từ máy chủ.

2. Mở trình duyệt web tới http://localhost:3000. Bạn sẽ thấy ứng dụng của mình.

3. Thêm một hoặc hai mục và xem nó hoạt động như bạn mong đợi. Bạn có thể đánh dấu các mục là hoàn chỉnh và xóa chúng.
 Frontend đang lưu trữ thành công các mục trong phần backend.


### CLI

Chạy lệnh `docker ps` trong terminal để liệt kê các container của bạn.


## Update the application

Trong các bước sau, bạn sẽ thay đổi `"empty text"` khi bạn không có bất kỳ mục nào trong danh sách việc cần làm thành `"You have no todo items yet! Add one above!"`

1. Thay đổi ở file src/static/js/app.js line 56

```
- <p className="text-center">No items yet! Add one above!</p>
+ <p className="text-center">You have no todo items yet! Add one above!</p>
```

2. Run docker build

```
 docker build -t getting-started .
```

3. Start container mới với code đã cập nhật.

```
docker run -dp 127.0.0.1:3000:3000 getting-started
```

Nó sẽ báo lỗi như thế này:

```
docker: Error response from daemon: driver failed programming external connectivity on endpoint laughing_burnell 
(bb242b2ca4d67eba76e79474fb36bb5125708ebdabd7f45c8eaf16caaabde9dd): Bind for 127.0.0.1:3000 failed: port is already allocated.
```

Đã xảy ra lỗi do bạn không thể khởi động container mới trong khi container cũ vẫn đang chạy. Lý do là container cũ đã sử dụng cổng 3000 của máy chủ và chỉ một tiến trình trên máy
 (bao gồm các container) có thể nghe một cổng cụ thể. Để khắc phục điều này, bạn cần loại bỏ container cũ.


4. Xoá container

```
- docker ps
- docker stop <the-container-id>
- docker rm <the-container-id>
```

Note: Bạn có thể dừng và xóa container trong một lệnh duy nhất bằng cách thêm  `-f` (force) vào lệnh docker rm. Ví dụ:

```
 docker rm -f <the-container-id>
```


5. Start lại

```
docker run -dp 127.0.0.1:3000:3000 getting-started
```
