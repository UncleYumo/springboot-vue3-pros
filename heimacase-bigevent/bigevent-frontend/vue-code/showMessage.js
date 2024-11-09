// // 简单的信息展示
// export function simpleMessage(msg) {
//     console.log(msg);
// }

// // 复杂的信息展示
// export function complexMessage(msg) {
//     console.log(new Date() + ": " + msg);
// }

// 简单的信息展示
function simpleMessage(msg) {
    console.log(msg);
}

// 复杂的信息展示
function complexMessage(msg) {
    console.log(new Date() + ": " + msg);
}

// 批量导出
// export {simpleMessage, complexMessage}

// 默认导出
export default {simpleMessage, complexMessage};
