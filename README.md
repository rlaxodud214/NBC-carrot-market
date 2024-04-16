## 기능 구현(요구사항)
___

### 개인 목표
- [ ] ConstraintLayout 제대로 사용해보기(depth 2이상 넘지 말기)
- [ ] 어댑터뷰 쓰면서 각 메소드 및 로직의 흐름 완벽히 이해하기

___

### 1. [필수] 메인 페이지
- [x] 디자인 및 화면 구성을 최대한 동일하게 해주세요. (사이즈 및 여백도 최대한 맞춰주세요.) ✨
- [x] 상품 데이터는 아래 dummy data 를 사용합니다. (더미 데이터는 자유롭게 추가 및 수정 가능)
- [x] 더미 데이터 : 이미지 링크,  상품 리스트 링크  (←링크 권한 없으면 여기 클릭)
- [x] RecyclerViewer를 이용해 리스트 화면을 만들어주세요.
- [x] 상단 툴바를 제거하고 풀스크린 화면으로 세팅해주세요. (상태바(시간/배터리 표시하는 최상단바)는 남기고)
- [x] 상품 이미지는 모서리를 라운드 처리해주세요.
- [x] 상품 이름은 최대 두 줄이고, 그래도 넘어가면 뒷 부분에 …으로 처리해주세요.
- [x] 뒤로가기(BACK)버튼 클릭시 종료하시겠습니까? [확인][취소] 다이얼로그를 띄워주세요. (예시 비디오 참고)
- [x] 상단 종모양 아이콘을 누르면 Notification을 생성해 주세요. (예시 비디오 참고)
- [x] 상품 가격은 1000단위로 콤마(,) 처리해주세요.
- [x] 상품 아이템들 사이에 회색 라인을 추가해서 구분해주세요.
- [x] 상품 선택시 아래 상품 상세 페이지로 이동합니다.
- [x] 상품 상세페이지 이동시 intent로 객체를 전달합니다. (Parcelize 사용)


### 2. [필수] 디테일 페이지
- [ ] 디자인 및 화면 구성을 최대한 동일하게 해주세요. (사이즈 및 여백도 최대한 맞춰주세요.) ✨
- [ ] 메인화면에서 전달받은 데이터로 판매자, 주소, 아이템, 글내용, 가격등을 화면에 표시합니다.
- [ ] 하단 가격표시 레이아웃을 제외하고 전체화면은 스크롤이 되어야합니다. (예시 비디오 참고)
- [ ] 상단 < 버튼을 누르면 상세 화면은 종료되고 메인화면으로 돌아갑니다.

___

### 3. [선택 - 🔥약간 매운맛] 스크롤 상단 이동!
- [ ] 스크롤을 최상단으로 이동시키는 플로팅 버튼 기능 추가
- [ ] 플로팅 버튼은 스크롤을 아래로 내릴 때 나타나며, 스크롤이 최상단일때 사라집니다.
- [ ] 플로팅 버튼을 누르면 스크롤을 최상단으로 이동시킵니다.
- [ ] 플로팅 버튼은 나타나고 사라질때 fade 효과가 있습니다.
- [ ] 플로팅 버튼을 클릭하면(pressed) 아이콘 색이 변경됩니다.

### 4. [선택 - 🔥🔥매운맛] 상품 삭제하기!
- [ ] 상품을 롱클릭 했을때 삭제 여부를 묻는 다이얼로그를 띄우고
- [ ] 확인을 선택 시 해당 항목을 삭제하고 리스트를 업데이트한다.
- [ ] 해당 상품이 삭제되었는지 확인!!

### 5. [선택 - 🔥🔥🔥Very 매운맛] 좋아요 처리!!
- [ ] 상품 상세 화면에서 좋아요 선택시 아이콘 변경 및 Snackbar 메세지 표시
- [ ] 메인 화면으로 돌아오면 해당 상품에 좋아요 표시 및 좋아요 카운트 +1
- [ ] 상세 화면에서 좋아요 해제시 이전 상태로 되돌림
___

### Warning: Don'ts
- 아직 배우지 않은 기능은 쓰지말 것 (강의에서 배운 내용을 최대한 활용)
- GPT, Copilot등 AI를 사용한 코드 제출 금지