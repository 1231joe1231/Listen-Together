/**
 * 环境配置
 *
 * @type {string}
 */

let baseUrl = 'http://13.70.62.118';

if (process.env.NODE_ENV === "development") {
    baseUrl = "http://13.70.62.118:8080";
} else if (process.env.NODE_ENV === "test") {
    baseUrl = "http://13.70.62.118:8080";
} else if (process.env.NODE_ENV === "production") {
    baseUrl = "http://13.70.62.118:8080";
}

let isProduction = false;

if (process.env.NODE_ENV === "production") {
    isProduction = true;
}

export {
    baseUrl,
    isProduction
}
