import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom"; //navigate 함수 쓸려고

// .env로 부터 백엔드 URL 받아오기
const BACKEND_API_BASE_URL = import.meta.env.VITE_BACKEND_API_BASE_URL;

function JoinPage() {
    
    const navigate = useNavigate();

    //회원가입변수
    const [username, setUsername] = useState("");
    const [isUsernameValid, setIsUsernameValid] = useState(null); // null: 검사 전, true: 사용 가능, false: 중복
    const [password, setPassword] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [error, setError] = useState("");

    //username 값이 변경이 되면 함수가 실행된다.
    useEffect(() => {

        const checkUsername = async () => {
            if (username.length < 4) {
                setIsUsernameValid(null);
                return;
            }

            try {
                const res = await fetch(`${BACKEND_API_BASE_URL}/user/exist`, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    credentials: "include",
                    body: JSON.stringify({ username }),
                });

                const exists = await res.json();
                setIsUsernameValid(!exists);
            } catch {
                setIsUsernameValid(null);
            }
        }

        //입력이 멈추고 0.3초후에 백엔드에 중복여부 요청
        const delay = setTimeout(checkUsername, 300);
        return () => clearTimeout(delay);

    }, [username])


    // 회원 가입 이벤트
    const handleSignUp = async (e) => {

        e.preventDefault();
        setError("");

        //벨리데이션 체크
        if (
            username.length < 4 ||
            password.length < 4 ||
            nickname.trim() === "" ||
            email.trim() === ""
        ) {
            setError("입력값을 다시 확인해주세요. (모든 항목은 필수이며, ID/비밀번호는 최소 4자)");
            return;
        }

        try {

            const res = await fetch(`${BACKEND_API_BASE_URL}/user`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                credentials: "include", //쿠키포함
                body: JSON.stringify({ username, password, nickname, email }),
            });

            if (!res.ok) throw new Error("회원가입 실패");
            navigate("/login");

        } catch {
            setError("회원가입 중 오류가 발생했습니다.");
        }

    }

    //HTML 코드 작성
    return (
        <div>
            <h1>회원 가입</h1>

            <form onSubmit={handleSignUp}>
                <label>아이디</label>
                <input
                    type="text"
                    placeholder="아이디 (4자 이상)"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                    minLength={4}
                />

                {username.length >= 4 && isUsernameValid === false && (
                    <p>이미 사용 중인 아이디입니다.</p>
                )}
                {username.length >= 4 && isUsernameValid === true && (
                    <p>사용 가능한 아이디입니다.</p>
                )}

                <label>비밀번호</label>
                <input
                    type="password"
                    placeholder="비밀번호 (4자 이상)"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    minLength={4}
                />

                <label>닉네임</label>
                <input
                    type="text"
                    placeholder="닉네임"
                    value={nickname}
                    onChange={(e) => setNickname(e.target.value)}
                    required
                />
                
                <label>이메일</label>
                <input
                    type="email"
                    placeholder="이메일 주소"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <button type="submit" disabled={isUsernameValid !== true}>회원가입</button>
            </form>
        </div>
    );
}

export default JoinPage;