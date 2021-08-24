#!/usr/bin/env node

const { spawn } = require('child_process');
const path = require('path');

if (process.argv[2] && 
    (process.argv[2].includes("preso") || 
    process.argv[2].includes("prezo"))) {
    const child = spawn('/usr/bin/env', ['bash', path.join(__dirname, 'bootstrap.sh'), "-preso"], {stdio: 'inherit'});
} else {
    const child = spawn('/usr/bin/env', ['bash', path.join(__dirname, 'bootstrap.sh')], {stdio: 'inherit'});
}


