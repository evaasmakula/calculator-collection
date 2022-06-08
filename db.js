const {
    default: fs,
    createReadStream,
    createWriteStream,
} = require('fs');

const {
    Transform
} = require('stream')

const filePath = "C:/Users/USER/Downloads/bg.png"
const read = createReadStream(filePath)
const write = createWriteStream(filePath)

const process = new Transform({
    transform(data, encoding, callback) {
        // Encode and decode data here
    }
})

let data = read
let res = write.write(JSON.stringify(data, '', 4))