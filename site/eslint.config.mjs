import js from '@eslint/js';
import nextjs from '@next/eslint-plugin-next';
import compat from 'eslint-plugin-compat';
import prettier from 'eslint-plugin-prettier/recommended';
import react from 'eslint-plugin-react';
import reactHooks from 'eslint-plugin-react-hooks';
import ts from 'typescript-eslint';

export default ts.config(
    {
        files: ['**/*.{mjs,ts,tsx}'],
        languageOptions: {
            parserOptions: {
                projectService: true,
                tsconfigRootDir: import.meta.dirname,
            },
        },
        settings: {
            react: {
                version: 'detect',
            },
        },
    },

    js.configs.recommended,
    ts.configs.strictTypeChecked,
    ts.configs.stylisticTypeChecked,
    react.configs.flat.recommended,
    react.configs.flat['jsx-runtime'],
    reactHooks.configs['recommended-latest'],
    {
        name: 'next/recommended',
        plugins: { '@next/next': nextjs },
        rules: {
            ...nextjs.configs.recommended.rules,
        },
    },
    {
        ...compat.configs['flat/recommended'],
        settings: {
            // https://github.com/vercel/next.js/blob/b96772a649028556f3b3937ee6f11ec578c9fd81/packages/eslint-plugin-next/src/rules/no-unwanted-polyfillio.ts
            polyfills: [
                'Array.prototype.@@iterator',
                'Array.prototype.at',
                'Array.prototype.copyWithin',
                'Array.prototype.fill',
                'Array.prototype.find',
                'Array.prototype.findIndex',
                'Array.prototype.flatMap',
                'Array.prototype.flat',
                'Array.from',
                'Array.prototype.includes',
                'Array.of',
                'Function.prototype.name',
                'fetch',
                'Map',
                'Number.EPSILON',
                'Number.Epsilon',
                'Number.isFinite',
                'Number.isNaN',
                'Number.isInteger',
                'Number.isSafeInteger',
                'Number.MAX_SAFE_INTEGER',
                'Number.MIN_SAFE_INTEGER',
                'Number.parseFloat',
                'Number.parseInt',
                'Object.assign',
                'Object.entries',
                'Object.fromEntries',
                'Object.getOwnPropertyDescriptor',
                'Object.getOwnPropertyDescriptors',
                'Object.hasOwn',
                'Object.is',
                'Object.keys',
                'Object.values',
                'Reflect',
                'Set',
                'Symbol',
                'Symbol.asyncIterator',
                'String.prototype.codePointAt',
                'String.prototype.endsWith',
                'String.fromCodePoint',
                'String.prototype.includes',
                'String.prototype.@@iterator',
                'String.prototype.padEnd',
                'String.prototype.padStart',
                'String.prototype.repeat',
                'String.raw',
                'String.prototype.startsWith',
                'String.prototype.trimEnd',
                'String.prototype.trimStart',
                'URL',
                'URL.prototype.toJSON',
                'URLSearchParams',
                'WeakMap',
                'WeakSet',
                'Promise',
                'Promise.prototype.finally',
                'es2015', // Should be covered by babel-preset-env instead.
                'es2016', // contains polyfilled 'Array.prototype.includes', 'String.prototype.padEnd' and 'String.prototype.padStart'
                'es2017', // contains polyfilled 'Object.entries', 'Object.getOwnPropertyDescriptors', 'Object.values', 'String.prototype.padEnd' and 'String.prototype.padStart'
                'es2018', // contains polyfilled 'Promise.prototype.finally' and ''Symbol.asyncIterator'
                'es2019', // Contains polyfilled 'Object.fromEntries' and polyfilled 'Array.prototype.flat', 'Array.prototype.flatMap', 'String.prototype.trimEnd' and 'String.prototype.trimStart'
                'es5', // Should be covered by babel-preset-env instead.
                'es6', // Should be covered by babel-preset-env instead.
                'es7', // contains polyfilled 'Array.prototype.includes', 'String.prototype.padEnd' and 'String.prototype.padStart'
            ],
        },
    },

    // Oltava viimeinen tässä listassa!
    prettier,
);
