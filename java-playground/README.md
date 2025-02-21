# 구조

- Model
    - BuyPrice
    - LottoNumber(1~45)
    - Lotto(LottoNumber[6])

- View
    - InputManager
    - OutputManager

- Controller
    - Calculator(로또 몇개 맞췄는가 계산하는거)
    - LottoMachine(구매 가격 받고 로또 번호 생성하는거)
    - RandomGenerator(랜덤하게 생성하는거)
- Main
    - ?