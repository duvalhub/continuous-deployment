const express = require('express')
const app = express()

app.get("/", (req,res) => {
    res.send("hello from app");
});

let port = process.env.PORT ? process.env.PORT : 80
app.listen(port, () => {
    console.log(`Server started on port : ${port}`)
})