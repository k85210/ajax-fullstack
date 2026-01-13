
const PI = 3.14;

const add = (a, b) => a + b;

const sub = (a, b) => a - b;

const getRamdonNumber = () => Math.floor(Math.random() * 100) + 1;

// 預設匯出(只能有一個，可匯出屬性、物件或方法)
export default PI;

// 具名匯出
export { add, sub, getRamdonNumber };
