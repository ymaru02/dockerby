const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      // 로컬 확인용
      // target: 'http://localhost:8080',
      // 서버 확인용
      target: 'http://k6s205.p.ssafy.io:8482',
      changeOrigin: true,
    }),
  );
};
