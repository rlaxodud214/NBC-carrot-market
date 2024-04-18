package com.example.carrot_market.data.source

import com.example.carrot_market.R
import com.example.carrot_market.data.model.Post

object PostDataSource {
    var dummyData = mutableListOf(
        Post(
            id = 1,
            image = R.drawable.img_product1,
            name = "산지 한달 된 선풍기 팝니다.",
            content = "이사가서 필요가 없어졌어요 \n급하게 내놓습니다",
            seller = "대현동",
            price = 1_000,
            address = "서울 서대문구 창천동",
            likeCount = 13,
            chatCount = 25,
        ),
        Post(
            id = 2,
            image = R.drawable.img_product2,
            name = "김치냉장고",
            content = "이사로 인해 내놔요",
            seller = "안마담",
            price = 20_000,
            address = "인천 계양구 귤현동",
            likeCount = 8,
            chatCount = 28
        ),
        Post(
            id = 3,
            image = R.drawable.img_product3,
            name = "샤넬 카드지갑",
            content = "고퀄 지갑이구요. \n사용감이 있어서 싸게 내어둡니다",
            seller = "코코유",
            price = 10_000,
            address = "수성구 범어동",
            likeCount = 23,
            chatCount = 5
        ),
        Post(
            id = 4,
            image = R.drawable.img_product4,
            name = "금고",
            content = """
                금고
                떼서 가져가야 함. 
                대우월드마크센텀. 
                미국 이주 관계로 싸게 팝니다
            """.trimIndent(),
            seller = "Nicole",
            price = 10_000,
            address = "해운대구 우제2동",
            likeCount = 14,
            chatCount = 17
        ),
        Post(
            id = 5,
            image = R.drawable.img_product5,
            name = "갤럭시 Z플립3 팝니다",
            content = """
                갤럭시 Z플립3 그린 팝니다. 
                항시 케이스 씌워서 썼고 필름 한 장 챙겨드립니다. 
                화면에 살짝 스크래치 난 것 말고 크게 이상은 없습니다!
            """.trimIndent(),
            seller = "절명",
            price = 150_000,
            address = "연제구 연산제8동",
            likeCount = 22,
            chatCount = 9
        ),
        Post(
            id = 6,
            image = R.drawable.img_product6,
            name = "프라다 복조리백",
            content = "까임 오염 없고 상태 깨끗합니다. 정품 여부 모름",
            seller = "미니멀하게",
            price = 50_000,
            address = "수원시 영통구 원천동",
            likeCount = 25,
            chatCount = 16
        ),
        Post(
            id = 7,
            image = R.drawable.img_product7,
            name = "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
            content = """
                울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권
                (에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)
                1. 인원: 6명 기준입니다. 1인 10,000원 추가요금
                2. 장소: 북구 블루마시티, 32-33층
                3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비
                4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.
                5. 33층 옥상 야외 테라스 있음, 가스버너 있음
                6. 고기 굽기 가능
                7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.
                8. 층간소음 주의 부탁드립니다.
                9. 방3개, 화장실3개, 비데 3개
                10. 저희 집안이 쓰는 별장입니다.
            """.trimIndent(),
            seller = "굿리치",
            price = 150_000,
            address = "수원시 영통구 원천동",
            likeCount = 25,
            chatCount = 16,
        ),
        Post(
            id = 8,
            image = R.drawable.img_product8,
            name = "샤넬 탑핸들 가방",
            content = """
                샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 
                색상 : 블랙
                사이즈 : 25.5cm * 17.5cm * 8cm
                구성 : 본품더스트
                급하게 돈이 필요해서 팝니다 ㅠ ㅠ
            """.trimIndent(),
            seller = "난쉽",
            price = 180000,
            address = "동래구 온천제2동",
            likeCount = 31,
            chatCount = 7
        ),
        Post(
            id = 9,
            image = R.drawable.img_product9,
            name = "4행정 엔진분무기 판매합니다.",
            content = """
                3년 전에 사서 한 번 사용하고 그대로 둔 상태입니다. 
                요즘 사용은 안 해봤습니다. 
                그래서 저렴하게 내 놓습니다. 
                중고라 반품은 어렵습니다.
            """.trimIndent(),
            seller = "알뜰한",
            price = 30000,
            address = "원주시 명륜2동",
            likeCount = 7,
            chatCount = 28
        ),
        Post(
            id = 10,
            image = R.drawable.img_product10,
            name = "셀린느 버킷 가방",
            content = """
                22년 신세계 대전 구매입니다. 
                셀린느 버킷백. 
                구매해서 몇 번 사용했어요. 
                까짐, 스크래치 없습니다. 
                타지역에서 보내는 거라 택배로 진행합니다!
            """.trimIndent(),
            seller = "똑태현",
            price = 190000,
            address = "중구 동화동",
            likeCount = 40,
            chatCount = 6
        )
    )
}