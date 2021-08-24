#!/usr/bin/env node

const { spawn } = require('child_process');
const child = spawn('/usr/bin/env', ['bash', './bootstrap.sh'], {stdio: 'inherit'});


