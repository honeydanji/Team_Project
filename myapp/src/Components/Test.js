export default function Test() {

    return (
        <div className="w-screen h-screen relative overflow-hidden bg-white">
            <div className="w-[1402px] h-[35px]">
                <img
                    src="C:/K-Digital_3/MainProject_Frontend/myapp/src/img/logo.png"
                    className="w-[52px] h-[35px] absolute left-[16.5px] top-[13.5px] object-cover"
                    alt="Company logo"
                />
                <p className="absolute left-[475px] top-[22px] text-xl font-bold text-left text-black">
                    Services
                </p>
                <p className="absolute left-[645px] top-[22px] text-xl font-bold text-left text-black">
                    Products
                </p>
                <p className="absolute left-[815px] top-[22px] text-xl font-bold text-left text-black">
                    Company
                </p>
                <p className="absolute left-[1336px] top-[22px] text-xl text-left text-black">
                    <span className="text-xl font-bold text-left text-black">Log in </span>
                    <span className="text-xl text-left text-black"></span>
                </p>
            </div>
            <div className="w-[945px] h-[238px]">
                <p className="w-[821px] h-[83px] absolute left-[130px] top-[366px] text-[50px] font-bold text-left text-black">
                    다중 객체 이미지 판별 추정정 웹 서비스
                </p>
                <p className="w-[943px] absolute left-[132px] top-[484px] text-xl font-semibold text-left text-black">
                    <span className="w-[943px] text-xl font-semibold text-left text-black">
                        안녕하세요, 다중 객체 이미지 판별 추정 웹 서비스에 오신 것을 환영합니다
                    </span>
                    <br />
                    <span className="w-[943px] text-xl font-semibold text-left text-black">
                        저희 서비스는 이미지 처리, 분석, 객체 분할, 6D(x, y, z, rx, ry, rz) 정보 추출하여 제공합니다
                    </span>
                    <br />
                    <span className="w-[943px] text-xl font-semibold text-left text-black">
                        실제 이미지 객체 분류와 6D 정보 추출에 효과적으로 활용할 수 있는 기술을 구현하였으니 많은
                        이용 부탁드립니다
                    </span>
                </p>
            </div>
            <div className="w-[308px] h-[57px]">
                <div className="w-[158px] h-[57px]">
                    <div className="w-[158px] h-[57px] absolute left-[250.5px] top-[728.5px] rounded-[14px] bg-[#304386]" />
                    <p className="absolute left-[273px] top-[744px] text-xl font-bold text-left text-white">
                        Get started
                    </p>
                </div>
                <p className="absolute left-[431px] top-[744px] text-xl text-left text-black">
                    <span className="text-xl font-bold text-left text-black">Live demo</span>
                    <span className="text-xl text-left text-black">  </span>
                </p>
            </div>
        </div>
    );
}