#!/usr/bin/env node

const { spawn } = require('child_process');
const path = require('path');

const child = spawn('/usr/bin/env', ['bash', path.join(__dirname, 'bootstrap.sh')]+process.argv.slice(2), {stdio: 'inherit'});


