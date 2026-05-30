import { type Options } from 'prettier';

const prettierConfig: Options = {
    endOfLine: 'auto',
    bracketSameLine: true,
    jsxSingleQuote: true,
    printWidth: 120,
    singleQuote: true,
    tabWidth: 4,
    plugins: ['@trivago/prettier-plugin-sort-imports'],
    importOrder: [
        '^((react|next)(.*)$)|^((react|next)$)',
        '<THIRD_PARTY_MODULES>',
        '^(@mui/material/(.*)$)',
        '^(@mui/icons-material/(.*)$)',
        '^@/(components|lib|hooks|util)/(.*)$',
        '^[./]',
    ],
    importOrderSeparation: true,
    importOrderSortSpecifiers: true,
};

export default prettierConfig;
